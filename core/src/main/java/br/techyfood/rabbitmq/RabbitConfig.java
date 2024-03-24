package br.techyfood.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setRetryTemplate(retryTemplate());
        rabbitTemplate.setExchange(EXCHANGE);
        return rabbitTemplate;
    }

    public RetryTemplate retryTemplate() {
        var retryTemplate = new RetryTemplate();

        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy());

        return retryTemplate;
    }

    public ExponentialBackOffPolicy exponentialBackOffPolicy() {
        var backOffPolicy = new ExponentialBackOffPolicy();

        backOffPolicy.setInitialInterval(1000);
        backOffPolicy.setMultiplier(2);
        backOffPolicy.setMaxInterval(60000);

        return backOffPolicy;
    }

}
