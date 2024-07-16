package br.com.sp.notificacoes.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
	
	private final String QUEUE_NAME = "usuario-email";
	private final String DIRECT_EXCHANGE_NAME = "PostUsuarioDirect";
	private final String BINDING_KEY = "cadastro.usuario";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true, false, false);
	}
	
	@Bean
	public DirectExchange exchangeDirect() {
		return new DirectExchange(DIRECT_EXCHANGE_NAME, true, false);
	}
	
	@Bean
	public Binding bindingKey() {
		return BindingBuilder.bind(queue()).to(exchangeDirect()).with(BINDING_KEY);
	}
	
	/*
	 * Essa classe é responsável por realizar as conversões do payload da mensagem, de string para object
	 */
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		return new Jackson2JsonMessageConverter(mapper);
	}
}