# kafka_Producer

## Environment
Quick start zookeeper and kafka server with astrea.  
`https://github.com/skiptests/astraea`

1. setup ip in `/etc/hosts`.
2. run `./docker/start_zookeeper.sh` to start zookeeper.
3. run `./docker/start_broker.sh zookeeper.connect=<ip>:<port>` to join kafka broker

### build
1. move `lib` directory from jars to repo directory
2. run `./build.sh`

### Execute
1. start the consumer with command `java -jar jars/consumer.jar <topic name> <broker address>`  
2. send some message with producer `java -jar jars/consumer.jar --topic <topic name> --brokers <broker address> --records <number of records> --recordSize <size of each record>`
