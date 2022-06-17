package com.pukul6.api.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MailRequest {
	@NotNull(message = "cannot be null")
	@NotEmpty(message = "cannot be empty")
	@NotBlank(message = "cannot be blank")
	@Email(message = "email not valid")
	private String to;
	
	private String cc;
	private String bcc;
	
	@NotNull(message = "cannot be null")
	@NotEmpty(message = "cannot be empty")
	@NotBlank(message = "cannot be blank")
	private String subject;
}
