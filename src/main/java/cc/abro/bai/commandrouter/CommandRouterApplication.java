package cc.abro.bai.commandrouter;

import cc.abro.bai.commandrouter.model.data.PrefixMapping;
import cc.abro.bai.commandrouter.model.dto.AddListener;
import cc.abro.bai.commandrouter.model.dto.ReceiveMessage;
import cc.abro.bai.commandrouter.model.dto.RemoveListener;
import cc.abro.bai.commandrouter.model.repositories.PrefixMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.stream.Stream;

@SpringBootApplication
public class CommandRouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandRouterApplication.class, args);
    }
}