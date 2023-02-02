package com.booking.eventcollectorapi.models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="visit_event", schema="db")
public class VisitEvent {
    @Id private UUID id;
    @Column private int entityType;
    @Column private UUID entityId;
    @Column private UUID sessionId;
    @Column private String eventType;
    @Column private String medium;
    @Column private Date createdAt;

    public VisitEvent(UUID id, int entityType, UUID entityId, UUID sessionId, String eventType, String medium, Date createdAt){
        this.id = id;
        this.entityType = entityType;
        this.entityId = entityId;
        this.sessionId = sessionId;
        this.eventType = eventType;
        this.medium = medium;
        this.createdAt = createdAt;
    }

    public VisitEvent() {}

    public UUID getId() {
        return id;
    }

    public int getEntityType() {
        return entityType;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getMedium() {
        return medium;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
