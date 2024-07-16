package br.com.sp.notificacoes.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import br.com.sp.emailDTO.output.EmailRecordDTO;
import br.com.sp.notificacoes.models.EmailModel;
import br.com.sp.notificacoes.services.EmailService;

public class UsuarioEmailConsumer {
	
	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "usuario-email")
	public void listenerDefaultEmail(@Payload EmailRecordDTO emailRecordDto) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailRecordDto, emailModel);
		
		emailService.sendEmail(emailModel);
	}
}
