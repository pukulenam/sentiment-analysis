package com.pukul6.api.controllers;

import com.pukul6.api.entities.dtos.MailRequest;
import com.pukul6.api.services.EmailService;
import com.pukul6.api.utilities.ApiValidation;
import dev.ditsche.mailo.MailAddress;
import dev.ditsche.mailo.factory.MailBuilder;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/email"})
public class EmailController {
	private final EmailService emailService;
	@Value(value = "${spring.email.name}")
	private String myMail;
	@Value(value = "${spring.email.me}")
	private String me;
	
	@PostMapping
	public ResponseEntity<?> send(@RequestBody @Valid MailRequest request, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest()
					.body(new HashMap<>() {{
						put("status", false);
						put("message", "failed to send email to: " + request.getTo());
						put("errors", ApiValidation.getErrorMessages(errors));
					}});
		}
		
		try {
			return ResponseEntity.ok()
					.body(new HashMap<>() {{
						put("status", emailService.send(
								MailBuilder.mjml()
										.from(new MailAddress(myMail, me))
										.to(new MailAddress(request.getTo()))
										.subject(request.getSubject())
										.loadTemplate("mjml/index.mjml")
						));
						put("message", "success");
						put("errors", null);
					}});
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new HashMap<>() {{
						put("status", false);
						put("message", "cannot send email");
						put("emssage", e.getMessage());
					}});
		}
	}
}
