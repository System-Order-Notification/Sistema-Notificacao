package br.com.sp.notificacoes.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.sp.notificacoes.models.EmailModel;
import br.com.sp.notificacoes.models.enums.StatusEmail;
import br.com.sp.notificacoes.repositories.EmailRepository;
import jakarta.transaction.Transactional;

@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String emailFrom;

	public EmailService() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public EmailModel sendEmail(EmailModel emailModel) {
		try {
			emailModel.setSendDateEmail(LocalDateTime.now());
			emailModel.setEmailFrom(emailFrom);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailModel.getEmailTo());
			message.setFrom(emailModel.getEmailFrom());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());

			javaMailSender.send(message);

			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
			// TODO: handle exception
		} finally {
			return emailRepository.save(emailModel);
		}
	}
}
