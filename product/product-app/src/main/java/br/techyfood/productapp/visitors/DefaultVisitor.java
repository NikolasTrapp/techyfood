package br.techyfood.productapp.visitors;

import br.techyfood.rabbitmq.model.Operation;
import br.techyfood.rabbitmq.model.ReplicationMessage;

public class DefaultVisitor {

    protected ReplicationMessage<?> buildReplicationMessage(Object entity, Operation operation) {
        return ReplicationMessage.builder()
                .operation(operation)
                .entity(entity)
                .build();
    }
}
