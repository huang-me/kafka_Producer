# kafka_Producer

## Environment
Quick start zookeeper and kafka server with astrea.  
`https://github.com/skiptests/astraea`

1. setup ip in `/etc/hosts`.
2. run `./docker/start_zookeeper.sh` to start zookeeper.
3. run `./docker/start_broker.sh zookeeper.connect=<ip>:<port>` to join kafka broker

### Add kafka lib/ in repo directory or add to class path
`./build.sh` will generate jars/ automatically
