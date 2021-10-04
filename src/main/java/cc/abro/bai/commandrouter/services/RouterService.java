package cc.abro.bai.commandrouter.services;

import cc.abro.bai.commandrouter.model.data.PrefixMapping;
import cc.abro.bai.commandrouter.model.dto.ReceiveMessage;
import cc.abro.bai.commandrouter.model.repositories.PrefixMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouterService {

    private final PrefixMappingRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplateJson;

    public void receiveMessage(ReceiveMessage message) {
        for (String topic : getListenerTopics(message.getText())) {
            kafkaTemplateJson.send(topic, message);
        }
    }

    private Set<String> getListenerTopics(String text) {
        return repository.findAll().stream()
                .filter(mapping -> text.startsWith(mapping.getPrefix()))
                .map(PrefixMapping::getTopicName)
                .collect(Collectors.toSet());
    }
}
