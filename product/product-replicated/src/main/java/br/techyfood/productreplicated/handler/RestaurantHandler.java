package br.techyfood.productreplicated.handler;

import br.techyfood.productreplicated.entities.RestaurantEntity;
import br.techyfood.productreplicated.repositories.RestaurantRepository;
import br.techyfood.rabbitmq.model.Operation;
import br.techyfood.rabbitmq.model.ReplicationMessage;
import br.techyfood.restaurantexternal.dtos.RestaurantDto;
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
import static br.techyfood.restaurantexternal.RestaurantExternalConstants.RESTAURANT_ENTITY_EVENT;

@Component
@RequiredArgsConstructor
public class RestaurantHandler {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = RESTAURANT_ENTITY_EVENT,
                    durable = "true"
            ),
            exchange = @Exchange(value = EXCHANGE, type = ExchangeTypes.TOPIC),
            key = RESTAURANT_ENTITY_EVENT
    ))
    public void handleReplicationEvent(@Payload ReplicationMessage<RestaurantDto> payload) {
        if (Operation.DELETE.equals(payload.getOperation())) {
            restaurantRepository.deleteById(payload.getEntity().getId());
            return;
        }

        var restaurantEntity = convert(payload.getEntity(), RestaurantEntity.class);
        restaurantRepository.save(restaurantEntity);
    }
}
