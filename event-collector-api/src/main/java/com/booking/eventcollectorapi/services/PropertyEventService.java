package com.booking.eventcollectorapi.services;

import com.booking.eventcollectorapi.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PropertyEventService extends BaseVisitEventService{
    @Autowired private PropertyRepository propertyRepository;

    @Override
    public boolean ValidateEntity(UUID id) {
        var property = propertyRepository.GetPropertyById(id);
        return property.isPresent() && property.get().getId().equals(id);
    }
}
