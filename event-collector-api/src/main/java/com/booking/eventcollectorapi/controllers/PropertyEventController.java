package com.booking.eventcollectorapi.controllers;

import com.booking.eventcollectorapi.exceptions.EntityNotFoundException;
import com.booking.eventcollectorapi.models.CreateEventModel;
import com.booking.eventcollectorapi.models.Entity.VisitEvent;
import com.booking.eventcollectorapi.models.EntityType;
import com.booking.eventcollectorapi.services.PropertyEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("property/{entityId}/events")
public class PropertyEventController {
    @Autowired private PropertyEventService propertyEventService;
    private final Logger logger= LoggerFactory.getLogger(PropertyEventController.class);

    @GetMapping("/")
    public List<VisitEvent> getAllPropertyVisitEvents(@PathVariable("entityId") UUID propertyId){
        return propertyEventService.GetAllVisitEventsByEntityTypeAndEntityId(EntityType.Property.value, propertyId);
    }

    @PostMapping("/{sessionId}")
    public VisitEvent createPropertyVisitEvent(
            @PathVariable("entityId") UUID entityId,
            @PathVariable("sessionId") UUID sessionId,
            @RequestBody CreateEventModel model) throws EntityNotFoundException {
        var event = new VisitEvent(
                UUID.randomUUID(),
                EntityType.Property.value,
                entityId,
                sessionId,
                model.getEventType(),
                model.getMedium(),
                Date.from(Instant.now())
                );
        return propertyEventService.CreateVisitEvent(event);
    }
}
