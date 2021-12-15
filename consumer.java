package mykafka;

import java.util.Properties;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class consumer {
    public static void main(String[] args) throws Exception {
        if(args.length < 2){
            System.out.println("Usage: <topic> <address>");
            return;
        }
        
        String topic = args[0].toString();
        String addr = args[1].toString();
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", addr);
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                System.out.println(record.key().length());
            }
        }
    }  
}