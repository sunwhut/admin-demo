package kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadProducer {
    private final KafkaProducer<String, String> producer;

    public static final String TOPIC = "test";

    private MultiThreadProducer(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<String, String>(props);
    }

    public void produce(){
        int messageNo = 0;
        int COUNT = 20;
        ProducerRecord record = null;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        while (messageNo < COUNT){
            String data = String.format("this is message %d!", messageNo);
            record = new ProducerRecord<String, String>(TOPIC, data);
            executor.submit(new KafkaProducerThread(producer, record));
            messageNo++;
        }
        producer.close();
        executor.shutdown();
    }

    public static void main(String[] args) {
        new MultiThreadProducer().produce();
    }
}

class KafkaProducerThread implements Runnable{
    private KafkaProducer<String, String> producer = null;
    private ProducerRecord<String, String> record = null;

    public KafkaProducerThread(KafkaProducer<String, String> producer, ProducerRecord<String, String> record){
        this.producer = producer;
        this.record = record;
    }

    public void run() {
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null){
                    System.out.println("send message occurs exception: " + exception);
                }
                if (metadata != null){
                    System.out.println("current thread: " + Thread.currentThread().getName()
                        + ", topic: " + metadata.topic() + ", partition: " + metadata.partition()
                        + ", offset: " + metadata.offset());
                }

            }
        });
    }
}




