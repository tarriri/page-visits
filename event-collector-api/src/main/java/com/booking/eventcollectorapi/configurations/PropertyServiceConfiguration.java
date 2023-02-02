package com.booking.eventcollectorapi.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix="property-service")
@Validated
public record PropertyServiceConfiguration(@NotBlank String host)
{

}
