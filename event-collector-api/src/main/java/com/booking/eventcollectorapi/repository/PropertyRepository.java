package com.booking.eventcollectorapi.repository;

import com.booking.eventcollectorapi.configurations.PropertyServiceConfiguration;
import com.booking.eventcollectorapi.models.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class PropertyRepository {
    private final WebClient client;
    private final Logger logger= LoggerFactory.getLogger(PropertyRepository.class);;

    public PropertyRepository(WebClient.Builder builder, PropertyServiceConfiguration config){
        client = builder
                .baseUrl(config.host())
                .build();
    }

    public Optional<Property> GetPropertyById(UUID id){
        String uri = String.format("/properties/%s", id.toString());
        Property property = client.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Property>() {})
                .block();

        return Optional.of(property);
    }
}
