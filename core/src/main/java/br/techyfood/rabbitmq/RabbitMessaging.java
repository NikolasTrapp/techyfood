package br.techyfood.rabbitmq;

import br.techyfood.rabbitmq.model.ReplicationMessage;

public interface RabbitMessaging {

    /**
     * This method request data of type T from other microsservice synchronously.
     */
    <T> T requestMessage(String exchange, String routingKey, Object payload, Class<T> referenceType, boolean retry);
    void sendMessageAsync(String exchange, String routingKey, Object payload, boolean retry);
    <T> void replicateCUDEvent(String exchange, String routingKey, ReplicationMessage<T> replicationMessage);
}
