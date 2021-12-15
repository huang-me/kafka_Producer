# kafka_Producer

## Environment
Quick start zookeeper and kafka server with astrea.  
`https://github.com/skiptests/astraea`

1. setup ip in `/etc/hosts`.
2. run `./docker/start_zookeeper.sh` to start zookeeper.
3. run `./docker/start_broker.sh zookeeper.connect=<ip>:<port>` to join kafka broker

## Execution
1. build producer and consumer with following commands.  
  `javac producer.java` or `javac consumer.java`
2. run producer with arguments --brokers --topic --records --recordSize  
   eg. `java -jar your_jar --brokers 192.168.10.2:11111 --topic chia --records 10000 --recordSize 1000`
