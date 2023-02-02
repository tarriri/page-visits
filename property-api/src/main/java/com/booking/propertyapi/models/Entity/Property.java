package com.booking.propertyapi.models.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="property", schema="db")
public class Property {
    @Id
    private UUID id;

    @Column
    private String name;

    public Property(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    public Property(){}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
