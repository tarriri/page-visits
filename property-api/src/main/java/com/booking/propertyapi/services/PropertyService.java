package com.booking.propertyapi.services;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booking.propertyapi.models.Entity.Property;
import com.booking.propertyapi.models.DTO.PropertyDTO;
import com.booking.propertyapi.repositories.PropertyRepository;

@Component
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<PropertyDTO> GetAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(a -> new PropertyDTO(a.getId(), a.getName()))
                .toList();
    }

    public PropertyDTO GetPropertyById(UUID id) {
        var property = propertyRepository.findById(id);
        if (property.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return new PropertyDTO(property.get().getId(), property.get().getName());
    }

    public PropertyDTO CreateProperty(String name) {
        var property = propertyRepository.saveAndFlush(new Property(UUID.randomUUID(), name));
        return new PropertyDTO(property.getId(), property.getName());
    }
}
