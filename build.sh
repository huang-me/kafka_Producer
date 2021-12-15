#!/bin/bash

javac -cp .:lib/* producer.java -d .
javac -cp .:lib/* consumer.java -d .
cp lib/ jars/. -r
jar cmvf MANIFEST_producer.MF jars/producer.jar mykafka
jar cmvf MANIFEST_consumer.MF jars/consumer.jar mykafka
