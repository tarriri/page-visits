FROM confluentinc/cp-kafka:latest

COPY ../../event-outbox-processor/docker/kafka-connect/confluentinc-kafka-connect-jdbc-*/ ./project/
COPY ../../event-outbox-processor/docker/kafka-connect/*.properties ./
COPY ../../event-outbox-processor/docker/kafka-connect/event-outbox-processor-*-all.jar ./project/plugins/event-collector-api.jar

ENTRYPOINT ["/bin/connect-standalone", "worker-standalone.properties", "postgres-source.properties"]