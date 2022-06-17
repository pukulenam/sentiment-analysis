package com.pukul6.api.services;

import dev.ditsche.mailo.factory.MailBuilder;
import dev.ditsche.mailo.provider.MailProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
	private final MailProvider mailProvider;
	
	public boolean send(MailBuilder mailBuilder) {
		return this.mailProvider.send(mailBuilder);
	}
}
