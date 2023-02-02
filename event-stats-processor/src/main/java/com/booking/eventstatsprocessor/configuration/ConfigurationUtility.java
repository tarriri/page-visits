package com.booking.eventstatsprocessor.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix="stats-processor")
@Validated
public record ConfigurationUtility(
        @NotBlank String applicationId,
        @NotBlank String bootstrapServers,
        @NotBlank long windowSize,
        @NotBlank long refreshInterval,
        @NotBlank String sourceTopic,
        @NotBlank String targetTopic
)  {

}
