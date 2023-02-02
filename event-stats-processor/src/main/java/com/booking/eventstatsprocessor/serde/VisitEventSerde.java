package com.booking.eventstatsprocessor.serde;

import com.booking.eventstatsprocessor.data.VisitEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class VisitEventSerde extends Serdes.WrapperSerde<VisitEvent> {
    public VisitEventSerde(JsonSerializer<VisitEvent> serializer, JsonDeserializer<VisitEvent> deserializer) {
        super(serializer, deserializer);
    }

    public VisitEventSerde() {
        super(new JsonSerializer<VisitEvent>(), new JsonDeserializer<VisitEvent>(VisitEvent.class));
    }
}
