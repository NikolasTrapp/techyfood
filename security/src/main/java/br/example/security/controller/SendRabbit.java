package br.example.security.controller;

import br.example.security.config.rabbitmq.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SendRabbit {

    private final RabbitMQProducer rabbitMQProducer;

    @GetMapping("send")
    public void send() {
        rabbitMQProducer.sendMessageToQueue("example", "oooiiiiiiii");
    }


}
