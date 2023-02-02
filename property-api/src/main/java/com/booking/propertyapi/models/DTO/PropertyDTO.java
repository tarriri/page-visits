package com.booking.propertyapi.models.DTO;

import java.util.UUID;

public class PropertyDTO {
    private UUID id;
    public UUID getId() {
        return id;
    }
    private String name;
    public String getName() {
        return name;
    }

    public PropertyDTO(UUID id, String name){
        this.id = id;
        this.name = name;
    }
}
