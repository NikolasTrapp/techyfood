package br.techyfood.restaurantreplicated.handler;

import br.techyfood.rabbitmq.model.Operation;
import br.techyfood.rabbitmq.model.ReplicationMessage;
import br.techyfood.restaurantreplicated.entities.UserEntity;
import br.techyfood.restaurantreplicated.repositories.UserRepository;
import br.techyfood.security.securityexternal.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static br.techyfood.core.Utils.convert;
import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;
import static br.techyfood.security.securityexternal.SecurityExternalConstants.USER_ENTITY_EVENT;

@Component
@RequiredArgsConstructor
public class UserReplicationHandler {

    private final UserRepository userRepository;

    @Transactional
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = USER_ENTITY_EVENT,
                    durable = "true"
            ),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC),
            key = USER_ENTITY_EVENT
    ))
    public void handleReplicationEvent(@Payload ReplicationMessage<UserDto> payload) {
        if (Operation.DELETE.equals(payload.getOperation())) {
            userRepository.deleteById(payload.getEntity().getId());
            return;
        }

        var userEntity = convert(payload.getEntity(), UserEntity.class);
        userRepository.save(userEntity);
    }
}
