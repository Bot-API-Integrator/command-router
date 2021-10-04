package cc.abro.bai.commandrouter.model.repositories;

import cc.abro.bai.commandrouter.model.data.PrefixMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefixMappingRepository extends MongoRepository<PrefixMapping, String> {
    List<PrefixMapping> findByTopicName(String topicName);
    Long deleteByTopicName(String topicName);
}
