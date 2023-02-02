package com.booking.eventstatsprocessor.serde;

import com.booking.eventstatsprocessor.data.EntityStats;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class EntityStatsSerde extends Serdes.WrapperSerde<EntityStats> {
    public EntityStatsSerde(JsonSerializer<EntityStats> serializer, JsonDeserializer<EntityStats> deserializer) {
        super(serializer, deserializer);
    }

    public EntityStatsSerde(){
        super(new JsonSerializer<EntityStats>(), new JsonDeserializer<EntityStats>(EntityStats.class));
    }
}
