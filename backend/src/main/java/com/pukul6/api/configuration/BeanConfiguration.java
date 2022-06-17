package com.pukul6.api.configuration;

import com.pukul6.api.utilities.MySmtpConfig;
import dev.ditsche.mailo.config.MailoConfig;
import dev.ditsche.mailo.provider.MailProvider;
import dev.ditsche.mailo.provider.SmtpMailProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
	@Value(value = "${spring.email.username}")
	private String username;
	
	@Value(value = "${spring.email.password}")
	private String password;
	
	@Value(value = "${spring.email.host}")
	private String host;
	
	@Value(value = "${spring.email.port}")
	private Integer port;
	
	@Value(value = "${spring.email.mail.io.app.id}")
	private String appId;
	
	@Value(value = "${spring.email.mail.io.app.secret}")
	private String appSecret;
	
	
	@Bean
	public MailProvider mailProvider() {
		var mailo = MailoConfig.get();
		mailo.setMjmlAppId(this.appId);
		mailo.setMjmlAppSecret(this.appSecret);
		
		var config = MySmtpConfig.builder()
				.username(this.username)
				.password(this.password)
				.host(this.host)
				.port(this.port)
				.ssl(false)
				.build();
		return new SmtpMailProvider(config);
	}
}
