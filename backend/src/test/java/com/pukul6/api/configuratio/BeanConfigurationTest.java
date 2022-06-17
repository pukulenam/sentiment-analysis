package com.pukul6.api.configuratio;

import com.pukul6.api.services.EmailService;
import dev.ditsche.mailo.factory.MailBuilder;
import dev.ditsche.mailo.provider.MailProvider;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(value = {MockitoExtension.class})
@Tag(value = "beanConfigurationTest")
public class BeanConfigurationTest {
	@MockBean
	private MailProvider mailProvider;
	
	@Autowired
	private EmailService emailService;
	
	@Value(value = "${spring.email.username}")
	private String username;
	
	@Value(value = "${spring.email.password}")
	private String password;
	
	@Value(value = "${spring.email.host}")
	private String host;
	
	@Value(value = "${spring.email.port}")
	private Integer port;
	
	@Test
	public void testNotNull() {
		assertNotNull(this.mailProvider);
		assertNotNull(this.username);
		assertNotNull(this.password);
		assertNotNull(this.host);
		assertNotNull(this.port);
		System.out.println("\033\143");
		System.out.println(port);
		assertTrue(port.toString().contains("25"));
	}
	
	@Test
	public void testSend() {
		when(this.mailProvider.send(any(MailBuilder.class))).thenReturn(true);
	}
}
