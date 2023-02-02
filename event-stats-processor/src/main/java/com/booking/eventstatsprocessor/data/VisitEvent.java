package com.booking.eventstatsprocessor.data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class VisitEvent implements Serializable {
    private final UUID id;
    private final int entityType;
    private final UUID entityId;
    private final UUID sessionId;
    private final String eventType;
    private final String medium;
    private final Date createdAt;

    public VisitEvent(UUID id, int entityType, UUID entityId, UUID sessionId, String eventType, String medium, Date createdAt) {
        this.id = id;
        this.entityType = entityType;
        this.entityId = entityId;
        this.sessionId = sessionId;
        this.eventType = eventType;
        this.medium = medium;
        this.createdAt = createdAt;
    }

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
