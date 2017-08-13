package com.asd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


/**
 * Service sends emails.
 */
@Service
@Slf4j
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("spring.mail.username")
	private String emailSender;

	/**
	 * Sends email message.
	 *
	 * @param email   recipient's email
	 * @param subject email subject
	 * @param content email body
	 */
	public void sendEmailMessage(String email, String subject, String content, String fileName, InputStreamSource inputStream) {
		// Prepare message using a Spring helper
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
			message.setSubject(subject);
			message.setFrom(emailSender);
			message.setTo(email);
			message.setText(content);
			message.addAttachment(fileName, inputStream);
			// Send email
			mailSender.send(mimeMessage);
		} catch (MailException | MessagingException e) {
			log.warn("Email message hasn't sent: {}", e.getMessage());
		}
	}
}
