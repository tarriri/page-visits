package com.booking.eventcollectorapi.services;

import com.booking.eventcollectorapi.exceptions.EntityNotFoundException;
import com.booking.eventcollectorapi.models.Entity.VisitEvent;
import com.booking.eventcollectorapi.models.EntityType;

import java.util.List;
import java.util.UUID;

public interface IVisitEventService {
    public VisitEvent CreateVisitEvent(VisitEvent event) throws EntityNotFoundException;
    public List<VisitEvent> GetAllVisitEventsByEntityTypeAndEntityId(int entityType, UUID entityId);
    public boolean ValidateEntity(UUID id);
}
