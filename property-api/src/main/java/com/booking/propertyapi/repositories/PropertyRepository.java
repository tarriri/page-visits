package com.booking.propertyapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.propertyapi.models.Entity.Property;

public interface PropertyRepository extends JpaRepository<Property, UUID>
{
    
}
