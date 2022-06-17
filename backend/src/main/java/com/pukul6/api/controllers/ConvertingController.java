package com.pukul6.api.controllers;

import com.pukul6.api.entities.dtos.ConvertingDto;
import com.pukul6.api.entities.dtos.ConvertingFinantial;
import com.pukul6.api.services.ConvertingService;
import com.pukul6.api.utilities.ApiValidation;
import java.util.HashMap;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/api/v1/converting"})
public class ConvertingController {
	private final ConvertingService convertingService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(convertingService.findAll());
	}
//
//	@PostMapping(value = {"/add/finansial"})
//	public ResponseEntity<?> addFinansial(@RequestBody @Valid ConvertingFinantial request, Errors errors) {
//		if (errors.hasErrors()) {
//			return ResponseEntity.badRequest().body(ApiValidation.getErrorMessages(errors));
//		}
//
//		try {
//			var converting = this.convertingService.findById(request.getConvertingId()).orElseThrow(NoSuchElementException :: new);
//			converting.setFinansial("");
//		} catch (NoSuchElementException e) {
//			return ResponseEntity
//					.badRequest()
//					.body(new HashMap<>() {{
//						put("message", "converting with id: " + request.getConvertingId() + " not found");
//					}});
//		} catch (Exception e) {
//			return ResponseEntity
//					.internalServerError()
//					.body(new HashMap<>() {{
//						put("message", "internal server error:" + e.getMessage());
//					}});
//		}
//	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid ConvertingDto request, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(ApiValidation.getErrorMessages(errors));
		}
		
		try {
			var converting = convertingService.DtoToEntity(request);
			var saved = convertingService.save(converting);
			return ResponseEntity.ok(saved);
		} catch (NoSuchElementException e) {
			var message = new HashMap<String, Object>() {
				{
					put("message", e.getMessage());
				}
			};
			return ResponseEntity.badRequest().body(message);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
	}
}
