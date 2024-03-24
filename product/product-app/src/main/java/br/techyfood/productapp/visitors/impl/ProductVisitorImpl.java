package br.techyfood.productapp.visitors.impl;

import br.techyfood.productapp.visitors.ProductVisitor;
import br.techyfood.rabbitmq.RabbitMessaging;
import br.techyfood.productapp.enities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.techyfood.common.visitor.VisitorUtils.buildReplicationMessage;
import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;
import static br.techyfood.rabbitmq.model.Operation.CREATE;
import static br.techyfood.rabbitmq.model.Operation.DELETE;
import static br.techyfood.productexternal.ProductExternalConstants.PRODUCT_ENTITY_EVENT;

@Component
@RequiredArgsConstructor
public class ProductVisitorImpl implements ProductVisitor {

    private final RabbitMessaging messaging;

    @Override
    public void visitBeforeCreate(ProductEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterCreate(ProductEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, CREATE);
        messaging.replicateCUDEvent(EXCHANGE, PRODUCT_ENTITY_EVENT, replicationEvent);
    }

    @Override
    public void visitBeforeUpdate(ProductEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterUpdate(ProductEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, CREATE);
        messaging.replicateCUDEvent(EXCHANGE, PRODUCT_ENTITY_EVENT, replicationEvent);
    }

    @Override
    public void visitBeforeDelete(ProductEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterDelete(ProductEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, DELETE);
        messaging.replicateCUDEvent(EXCHANGE, PRODUCT_ENTITY_EVENT, replicationEvent);
    }
}
