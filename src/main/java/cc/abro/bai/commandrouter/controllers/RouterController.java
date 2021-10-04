package cc.abro.bai.commandrouter.controllers;

import cc.abro.bai.commandrouter.model.dto.AddListener;
import cc.abro.bai.commandrouter.model.dto.ReceiveMessage;
import cc.abro.bai.commandrouter.model.dto.RemoveListener;
import cc.abro.bai.commandrouter.services.RouterService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class RouterController {

    private final RouterService routerService;

    @KafkaListener(topics = "CommandRouterReceiveMessage", containerFactory = "kafkaListenerFactoryJson")
    public void receiveMessage(ReceiveMessage message) {
        System.out.println("Receive message: " + message);
        routerService.receiveMessage(message);
    }
}
