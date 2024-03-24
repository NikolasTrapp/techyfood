package br.techyfood.common.visitor;

import br.techyfood.rabbitmq.model.Operation;
import br.techyfood.rabbitmq.model.ReplicationMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VisitorUtils {

    public static ReplicationMessage<?> buildReplicationMessage(Object entity, Operation operation) {
        return ReplicationMessage.builder()
                .operation(operation)
                .entity(entity)
                .build();
    }
}
