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
public class CommandRouterApplication implements CommandLineRunner {

    @Autowired
    private PrefixMappingRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplateObject;

    public static void main(String[] args) {
        SpringApplication.run(CommandRouterApplication.class, args);
    }

    @Override
    public void run(String[] args) throws InterruptedException {
        System.out.println("TEST!!!");

        repository.deleteAll();
        repository.findAll().forEach(System.out::println);
        System.out.println("TEST 2!!!");

        // save a couple of customers
        repository.save(new PrefixMapping("Alice", "Smith"));
        repository.save(new PrefixMapping("Alice", "Smith2"));
        repository.save(new PrefixMapping("Bob", "Smith"));

        // fetch all customers
        System.out.println("-------------------------------");
        for (PrefixMapping customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();


        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (PrefixMapping customer : repository.findByPrefix("Alice")) {
            System.out.println(customer);
        }

        kafkaTemplate.send("testTopic", "testMessage");
        kafkaTemplateObject.send("CommandRouterAddTopic", new AddListener("prefix-1", "topicName-1"));
        kafkaTemplateObject.send("CommandRouterRemoveTopic", new RemoveListener("prefix-2", "topicName-2"));
        kafkaTemplateObject.send("CommandRouterReceiveMessage", new ReceiveMessage(null, null, null, null, null));

        /*StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
            sb.append("c");
        }
        TEST = sb.toString();

        for (int i = 0; i < PRODUCERS; i++) {
            startProducer(i);
        }

        while (true) {
            long sum = 0;
            for (int i = 0; i < PRODUCERS; i++) {
                sum += count[i];
            }
            System.out.println(sum/1000/1000 + "M");
            Thread.sleep(5000);
        }*/
    }

    static int PRODUCERS = 32;
    int[] count = new int[PRODUCERS];
    static String TEST = "";

    private void startProducer(int topicI) {
        new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                kafkaTemplate.send("topicName" + topicI, TEST);
                if (i%1000 == 0) {
                    count[topicI] = i;
                }
            }
        }).start();
    }
}