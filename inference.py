# import flask for API
import flask
from flask import Flask, request, jsonify

# padding kalimat dengan maxlen = 50
from tensorflow.keras.preprocessing.sequence import pad_sequences
import pickle
from tensorflow.keras import models

# untuk machine summarization 
from transformers import BertTokenizer, EncoderDecoderModel

# loading model dan tokenizer machine summarization
tokenizer = BertTokenizer.from_pretrained("cahya/bert2gpt-indonesian-summarization")
tokenizer.bos_token = tokenizer.cls_token
tokenizer.eos_token = tokenizer.sep_token
model = EncoderDecoderModel.from_pretrained("cahya/bert2gpt-indonesian-summarization")

# loading tokenizer
def load_tokenizer():
    with open('tokenizer.pickle', 'rb') as handle:
        tokenizer = pickle.load(handle)
    return tokenizer

# loading model
def load_model():
    model = models.load_model('best_model.h5')
    return model

# convert output sigmoid (0-1) jadi probabilitas
def convert_output_to_probability(predicted):
    if predicted < 0.5:
        proba_predicted = (0.5 - predicted)*2
    else:
        proba_predicted = (predicted - 0.5)*2
    return proba_predicted

# prediksi kelas dan probabilitas berdasarkan kalimat 
def tf_prediction(input_text, model, tokenizer):
    input_sequence = tokenizer.texts_to_sequences([input_text])
    input_padded = pad_sequences(input_sequence, maxlen=50, padding = 'post', truncating = 'post')
    predicted = model(input_padded)
    label_predicted = float(predicted.numpy()[0][0])
    class_predicted = 0 if label_predicted < 0.5 else 1 
    proba_predicted = convert_output_to_probability(label_predicted)
    return class_predicted, proba_predicted

# prediksi kelas dan probabilitas berdasarkan kalimat 
def tf_prediction_batch(list_text, model, tokenizer):
    input_sequence = tokenizer.texts_to_sequences(list_text)
    input_sequence = pad_sequences(input_sequence, maxlen=50, padding = 'post', truncating = 'post')
    predicted = model(input_sequence)
    label_predicted = float(predicted.numpy()[0][0])
    class_predicted = 0 if label_predicted < 0.5 else 1 
    proba_predicted = convert_output_to_probability(label_predicted)
    return class_predicted, proba_predicted

# menghitung jumlah kata
def count_word_length(text):
    return len(text.split())

# memasukkan kalimat ke dalam model machine summarization
def machine_summarization(article):
    input_ids = tokenizer.encode(article, return_tensors='pt', 
                                padding=True, truncation=True, 
                                max_length=512, add_special_tokens = True)
    summary_ids = model.generate(input_ids,
                min_length=5,
                max_length=20, 
                early_stopping=True,
                no_repeat_ngram_size=2,
                use_cache=True,
                do_sample = True,
                top_k = 50,
                top_p = 0.95)

    summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
    return summary_text


app = flask.Flask(__name__)

# test hellow world
@app.route("/home", methods = ["GET"])
def home():
    return "Hello World!"

# api untuk mengirimkan response json
@app.route("/api/text-sentiment-analysis", methods=["GET", "POST"])
def process_score_image_request():
    text = request.json["text"]
    word_len = count_word_length(text)
    if word_len > 50:
        text = machine_summarization(text)

    model = load_model()
    tokenizer = load_tokenizer()
    output_label, output_proba = tf_prediction(text, model, tokenizer)
    label2idx = {'down': 1 ,   'up': 0}
    idx2label = {y:x for x,y in label2idx.items()}
    response = {"label": idx2label[output_label], 
                "probability": output_proba}
    return jsonify(response)


app.run(host="0.0.0.0", port=5000)
