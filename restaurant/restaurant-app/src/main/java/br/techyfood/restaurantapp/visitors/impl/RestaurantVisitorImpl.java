package br.techyfood.restaurantapp.visitors.impl;

import br.techyfood.rabbitmq.RabbitMessaging;
import br.techyfood.restaurantapp.enities.RestaurantEntity;
import br.techyfood.restaurantapp.visitors.DefaultVisitor;
import br.techyfood.restaurantapp.visitors.RestaurantVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;
import static br.techyfood.rabbitmq.model.Operation.CREATE;
import static br.techyfood.rabbitmq.model.Operation.DELETE;
import static br.techyfood.restaurantexternal.RestaurantExternalConstants.RESTAURANT_ENTITY_EVENT;

@Component
@RequiredArgsConstructor
public class RestaurantVisitorImpl extends DefaultVisitor implements RestaurantVisitor {

    private final RabbitMessaging messaging;

    @Override
    public void visitBeforeCreate(RestaurantEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterCreate(RestaurantEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, CREATE);
        messaging.replicateCUDEvent(EXCHANGE, RESTAURANT_ENTITY_EVENT, replicationEvent);
    }

    @Override
    public void visitBeforeUpdate(RestaurantEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterUpdate(RestaurantEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, CREATE);
        messaging.replicateCUDEvent(EXCHANGE, RESTAURANT_ENTITY_EVENT, replicationEvent);
    }

    @Override
    public void visitBeforeDelete(RestaurantEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterDelete(RestaurantEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, DELETE);
        messaging.replicateCUDEvent(EXCHANGE, RESTAURANT_ENTITY_EVENT, replicationEvent);
    }
}
