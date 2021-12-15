package mykafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;

public class producer {

   public static void main(String[] args) throws Exception{

      // arguments setup
      Options op = new Options();
      Option topicOption = new Option("t", "topic", true, "topic to send");
      topicOption.setRequired(true);
      op.addOption(topicOption);

      Option serverOption = new Option("b", "brokers", true, "bootstrap server");
      serverOption.setRequired(true);
      op.addOption(serverOption);

      Option recordOption = new Option("r", "records", true, "amount of records");
      recordOption.setRequired(true);
      op.addOption(recordOption);

      Option sizeOption = new Option("s", "recordSize", true, "size of records");
      sizeOption.setRequired(true);
      op.addOption(sizeOption);

      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = null;
      try {
         cmd = parser.parse(op, args);
      } catch (ParseException e) {
         System.out.println(e.getMessage());
      }

      // Assign topicName to string variable
      String topicName = cmd.getOptionValue("topic");
      String addr = cmd.getOptionValue("brokers");
      Integer amount = Integer.parseInt(cmd.getOptionValue("records"));
      Integer size = Integer.parseInt(cmd.getOptionValue("recordSize"));
      System.out.printf("%s@%s\n", topicName, addr);

      // create instance for properties to access producer configs   
      Properties props = new Properties();
      props.put("bootstrap.servers", addr);
      props.put("acks", "0");
      props.put("retries", 0);
      props.put("linger.ms", 100);
      props.put("batch.size", "200000");
      props.put("compression.type", "lz4");
      props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

      String dummy = StringUtils.repeat("0", size);

      Producer<String, String> producer = new KafkaProducer<>(props);
      for (int i = 0; i < amount; i++) {
         String k = String.format("key-%s%d", dummy, i);
         String v = String.format("value-%d", i);
         producer.send(new ProducerRecord<String, String>(topicName, k, v));
      }

      producer.close();
      return;
   }
}