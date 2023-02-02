package com.booking.eventstatsprocessor.processors;

import com.booking.eventstatsprocessor.configuration.ConfigurationUtility;
import com.booking.eventstatsprocessor.data.EntityStats;
import com.booking.eventstatsprocessor.data.VisitEvent;
import com.booking.eventstatsprocessor.serde.EntityStatsSerde;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class VisitorCountProcessor {
    private final ConfigurationUtility config;

    public VisitorCountProcessor(ConfigurationUtility config) {
        this.config = config;
    }

    @Autowired
    void buildPipeline(StreamsBuilder builder){
        KStream<String, VisitEvent> source = builder.stream(config.sourceTopic());
        KStream<Windowed<String>, EntityStats> visitors = source
                .groupByKey()
                .windowedBy(TimeWindows
                        .ofSizeWithNoGrace(Duration.ofMillis(config.windowSize()))
                        .advanceBy(Duration.ofMillis(config.refreshInterval()))
                )
                .aggregate(
                        EntityStats::new,
                        (key, value, aggregate) -> aggregate.add(value),
                        Materialized.
                                <String, EntityStats, WindowStore<Bytes, byte[]>>as("event-aggregates")
                                .withValueSerde(new EntityStatsSerde())
                )
                .toStream();

        visitors.to(config.targetTopic(), Produced.keySerde(WindowedSerdes.timeWindowedSerdeFrom(String.class, config.windowSize())));
    }
}
