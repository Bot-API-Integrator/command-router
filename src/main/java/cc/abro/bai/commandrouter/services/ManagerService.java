package cc.abro.bai.commandrouter.services;

import cc.abro.bai.commandrouter.model.data.PrefixMapping;
import cc.abro.bai.commandrouter.model.dto.AddListener;
import cc.abro.bai.commandrouter.model.dto.ReceiveMessage;
import cc.abro.bai.commandrouter.model.dto.RemoveAllListeners;
import cc.abro.bai.commandrouter.model.dto.RemoveListener;
import cc.abro.bai.commandrouter.model.repositories.PrefixMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManagerService {

    private final PrefixMappingRepository repository;

    public void addListener(AddListener message) {
        PrefixMapping newMapping = new PrefixMapping(message.getPrefix(), message.getTopicName());
        if (repository.findOne(Example.of(newMapping)).isEmpty()) {
            repository.insert(newMapping);
        }
    }

    public void removeListener(RemoveListener message) {
        repository.delete(new PrefixMapping(message.getPrefix(), message.getTopicName()));
    }

    public void removeAllListeners(RemoveAllListeners message) {
        repository.deleteByTopicName(message.getTopicName());
    }
}
