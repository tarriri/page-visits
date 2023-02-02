package com.booking.eventcollectorapi.models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="outbox", schema="db")
public class Outbox {
    @Id private UUID id;
    @Column private String aggregateType;
    @Column private UUID aggregateId;
    @Column private String type;
    @Column private String payload;
    @Column private Date createdAt;

    public Outbox(UUID id, String aggregateType, UUID aggregateId, String type, String payload, Date createdAt){
        this.id = id;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public Outbox(){}

    public UUID getId() {
        return id;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
