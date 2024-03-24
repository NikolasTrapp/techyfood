package br.techyfood.rabbitmq;

public class RabbitConstants {

    public static final int RETRIES = 3;
    public static final long MESSAGES_DELAY = 3 * 1000; //3s
    public static final String EXCHANGE = "br.example.exchange.topic";
}
