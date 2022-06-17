package com.pukul6.api.utilities;

import dev.ditsche.mailo.config.SmtpConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MySmtpConfig extends SmtpConfig {
	public MySmtpConfig() {
		super();
	}
	
	@Builder
	public MySmtpConfig(String username, String password, String host, int port, boolean ssl) {
		super.setUsername(username);
		super.setPassword(password);
		super.setHost(host);
		super.setPort(port);
		super.setSsl(ssl);
	}
}
