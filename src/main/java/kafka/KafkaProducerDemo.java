package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerDemo {
    private final KafkaProducer<String, String> producer;

    public static final String TOPIC = "test";

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerDemo.class);

    private KafkaProducerDemo(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<String, String>(props);
    }

    public void produce(){
        int messageNo = 31;
        final int COUNT = 40;
        while (messageNo <= COUNT){
            String data = String.format("this is message %d!", messageNo);

            producer.send(new ProducerRecord<String, String>(TOPIC, data));

            messageNo++;
        }
        producer.close();
    }

    public static void main(String[] args) {
        new KafkaProducerDemo().produce();
    }

}
