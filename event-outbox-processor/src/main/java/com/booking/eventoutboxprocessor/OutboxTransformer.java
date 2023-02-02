package com.booking.eventoutboxprocessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OutboxTransformer<R extends ConnectRecord<R>> implements Transformation<R> {
    private final static Schema KEY_SCHEMA = Schema.STRING_SCHEMA;
    private final static String ID = "id";
    private final static String ENTITY_TYPE = "entityType";
    private final static String ENTITY_ID = "entityId";
    private final static String SESSION_ID = "sessionId";
    private final static String EVENT_TYPE = "eventType";
    private final static String MEDIUM = "medium";
    private final static String CREATED_AT = "createdAt";
    private final static Schema VISIT_EVENT_SCHEMA = SchemaBuilder
            .struct()
            .field(ID, Schema.STRING_SCHEMA)
            .field(ENTITY_TYPE, Schema.OPTIONAL_INT32_SCHEMA)
            .field(ENTITY_ID, Schema.STRING_SCHEMA)
            .field(SESSION_ID, Schema.STRING_SCHEMA)
            .field(EVENT_TYPE, Schema.STRING_SCHEMA)
            .field(MEDIUM, Schema.STRING_SCHEMA)
            .field(CREATED_AT, Schema.STRING_SCHEMA);

    private Logger logger = LoggerFactory.getLogger(OutboxTransformer.class);
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    @Override
    public R apply(R record) {
        var value = (Struct) record.value();
        var message_id = value.get("id").toString();
        logger.info(String.format("Message being processed with messageId: %s, aggregateType: %s, aggregateId: %s", message_id, value.get("aggregate_type"), value.get("aggregate_id")));
        var eventType = value.get("type");
        if(!eventType.equals("VisitEventCreated")){
            logger.info(String.format("Db row skipped due to eventType: %s", eventType));
            return null;
        }

        var payload = gson.fromJson(value.get("payload").toString(), Map.class);
        var entityType = ((Double) payload.get(ENTITY_TYPE)).intValue();
        var entityId = payload.get(ENTITY_ID);

        var struct = new Struct(VISIT_EVENT_SCHEMA)
                .put(ID, payload.get(ID))
                .put(ENTITY_TYPE, entityType)
                .put(ENTITY_ID, entityId)
                .put(SESSION_ID, payload.get(SESSION_ID))
                .put(EVENT_TYPE, payload.get(EVENT_TYPE))
                .put(MEDIUM, payload.get(MEDIUM))
                .put(CREATED_AT, payload.get(CREATED_AT));

        var key = String.format("%s-%s", entityType, entityId);
        return record.newRecord(
                record.topic(),
                record.kafkaPartition(),
                KEY_SCHEMA,
                key,
                VISIT_EVENT_SCHEMA,
                struct,
                record.timestamp()
        );
    }

    @Override
    public ConfigDef config() {
        return new ConfigDef();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}