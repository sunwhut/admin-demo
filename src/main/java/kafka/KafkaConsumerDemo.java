package kafka;

import org.apache.kafka.clients.consumer.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerDemo {
    private Consumer<String, String> consumer;

    private static String TOPIC = "test";

    private static String GROUP = "test";

    private KafkaConsumerDemo(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(properties);
    }

    private void autoCommit(){
        consumer.subscribe(Arrays.asList(TOPIC));

        while (true){
            ConsumerRecords<String, String> records = consumer.poll(1000);

            System.out.println("size: " + records.count());

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void manualCommit(){
        consumer.subscribe(Arrays.asList(TOPIC));

        final int minBatchSize = 20;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(1000);

            System.out.println("size: " + records.count());

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
                buffer.add(record);

                if (buffer.size() >= minBatchSize){
                    consumer.commitSync();
                    System.out.println("commit offset!");
                    buffer.clear();
                }
            }

        }
    }

    public static void main(String[] args) {
        KafkaConsumerDemo kafkaConsumerDemo = new KafkaConsumerDemo();
//        kafkaConsumerDemo.autoCommit();
        kafkaConsumerDemo.manualCommit();
    }
}
