package br.example.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReceiveRabbit {

    @RabbitListener(queues = "example")
    public void receiveMessage(String message) {
        log.info("Mensagem recebida. {}", message);
    }
}
