package cc.abro.bai.commandrouter.controllers;

import cc.abro.bai.commandrouter.model.dto.AddListener;
import cc.abro.bai.commandrouter.model.dto.RemoveAllListeners;
import cc.abro.bai.commandrouter.model.dto.RemoveListener;
import cc.abro.bai.commandrouter.services.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @KafkaListener(topics = "CommandRouterAddListener", containerFactory = "kafkaListenerFactoryJson")
    public void addListener(AddListener message) {
        System.out.println("Add: " + message);
        managerService.addListener(message);
    }

    @KafkaListener(topics = "CommandRouterRemoveListener", containerFactory = "kafkaListenerFactoryJson")
    public void removeListener(RemoveListener message) {
        System.out.println("Remove: " + message);
        managerService.removeListener(message);
    }

    @KafkaListener(topics = "CommandRouterRemoveAllListeners", containerFactory = "kafkaListenerFactoryJson")
    public void removeAllListeners(RemoveAllListeners message) {
        System.out.println("Remove all: " + message);
        managerService.removeAllListeners(message);
    }
}
