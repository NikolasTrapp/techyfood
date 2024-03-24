package br.techyfood.security.visitors.impl;

import br.techyfood.security.entities.UserEntity;
import br.techyfood.security.visitors.UserVisitor;
import br.techyfood.rabbitmq.RabbitMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.techyfood.common.visitor.VisitorUtils.buildReplicationMessage;
import static br.techyfood.rabbitmq.RabbitConstants.EXCHANGE;
import static br.techyfood.rabbitmq.model.Operation.*;
import static br.techyfood.security.securityexternal.SecurityExternalConstants.USER_ENTITY_EVENT;

@Component
@RequiredArgsConstructor
public class UserVisitorImpl implements UserVisitor {

    private final RabbitMessaging messaging;


    @Override
    public void visitBeforeCreate(UserEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterCreate(UserEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, CREATE);
        messaging.replicateCUDEvent(EXCHANGE, USER_ENTITY_EVENT, replicationEvent);
    }

    @Override
    public void visitBeforeUpdate(UserEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterUpdate(UserEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, UPDATE);
        messaging.replicateCUDEvent(EXCHANGE, USER_ENTITY_EVENT, replicationEvent);
    }

    @Override
    public void visitBeforeDelete(UserEntity entity) {
        // TODO
    }

    @Override
    public void visitAfterDelete(UserEntity entity) {
        var replicationEvent = buildReplicationMessage(entity, DELETE);
        messaging.replicateCUDEvent(EXCHANGE, USER_ENTITY_EVENT, replicationEvent);
    }
}
