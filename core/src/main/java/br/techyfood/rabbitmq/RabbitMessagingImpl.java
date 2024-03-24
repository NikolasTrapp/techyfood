package br.techyfood.rabbitmq;

import br.techyfood.rabbitmq.model.RabbitMQException;
import br.techyfood.rabbitmq.model.ReplicationMessage;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static br.techyfood.core.Utils.*;
import static br.techyfood.rabbitmq.RabbitConstants.*;
import static java.util.Objects.isNull;

@Slf4j
@Component("rabbitmq.RabbitMessagingImpl")
@RequiredArgsConstructor
public class RabbitMessagingImpl implements RabbitMessaging {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public <T> T requestMessage(String exchange, @NotEmpty String queue, Object payload, Class<T> referenceType, boolean retry) {
        return sendMessage(exchange, queue, payload, referenceType, retry);
    }

    @Override
    public void sendMessageAsync(String exchange, String routingKey, Object payload, boolean retry) {
        CompletableFuture.runAsync(() -> sendMessage(exchange, routingKey, payload, Object.class, retry));
    }

    @Override
    public <T> void replicateCUDEvent(String exchange, String routingKey, ReplicationMessage<T> replicationMessage) {
        sendMessageAsync(exchange, routingKey, replicationMessage, true);
    }

    private <T> T sendMessage(String exchange, String routingKey, Object payload, Class<T> expectedType, boolean retry) {
        var exchangeName = isEmpty(exchange) ? EXCHANGE : exchange;
        var typeReference = ParameterizedTypeReference.forType(expectedType);

        if (isNull(payload)) {
            throw new IllegalArgumentException("Payload cannot be null.");
        }

        var times = 1;
        Exception error;
        do {
            try {
                var result = rabbitTemplate.convertSendAndReceiveAsType(exchangeName, routingKey, payload, typeReference);
                return expectedType.cast(result);
            } catch (Exception e) {
                log.warn("Error while trying to send message to exchange: {}, routingKey: {}, with payload: {}. retry {} of {}. Waiting {}s for next attempt.",
                        exchangeName, routingKey, objectAsJsonString(payload), times, RETRIES, (MESSAGES_DELAY / 1000));
                sleep(MESSAGES_DELAY);
                error = e;
            }
            times ++;
        } while (times <= RETRIES && retry);

        var attempts = retry ? RETRIES : 1;
        var msg = format("Error while trying to send message to exchange: {}, routingKey: {}, with payload: {} after {} attemps.",
                exchangeName, routingKey, objectAsJsonString(payload), attempts);
        log.error(msg);
        throw new RabbitMQException(msg, error);
    }
}
