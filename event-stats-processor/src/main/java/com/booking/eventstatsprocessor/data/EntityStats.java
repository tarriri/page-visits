package com.booking.eventstatsprocessor.data;

import java.util.UUID;

public class EntityStats {
    private int entityType;
    private UUID entityId;
    private int visitors;

    public EntityStats(int entityType, UUID entityId, int visitors) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.visitors = visitors;
    }

    public EntityStats(){

    }

    public EntityStats add(VisitEvent event){
        if(event.getEntityType() != entityType || !event.getEntityId().equals(entityId)){
            throw new IllegalArgumentException(String.format("Event id %s doesn't belong to stat aggregation of entityType %s and entityId %s", event.getId(), entityType, entityId));
        }

        this.visitors++;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public int getVisitors() {
        return visitors;
    }
}
