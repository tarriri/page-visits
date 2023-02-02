package com.booking.eventcollectorapi.services;

import com.booking.eventcollectorapi.exceptions.EntityNotFoundException;
import com.booking.eventcollectorapi.models.Entity.Outbox;
import com.booking.eventcollectorapi.models.Entity.VisitEvent;
import com.booking.eventcollectorapi.repository.OutboxRepository;
import com.booking.eventcollectorapi.repository.VisitEventRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class BaseVisitEventService implements IVisitEventService {
    @Autowired
    private VisitEventRepository visitEventRepository;
    @Autowired
    private OutboxRepository outboxRepository;
    private Logger logger = LoggerFactory.getLogger(BaseVisitEventService.class);
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

    @Override
    @Transactional
    public VisitEvent CreateVisitEvent(VisitEvent event) throws EntityNotFoundException {
        var isValidEntity = this.ValidateEntity(event.getEntityId());
        if(!isValidEntity){
            throw new EntityNotFoundException();
        }

        var visitEvent = visitEventRepository.save(event);
        var outbox = CreateOutboxMessageFromVisitEvent(visitEvent);
//        if(outbox.getCreatedAt().after(Date.from(Instant.EPOCH))){
//            logger.info("making up an error");
//            throw new RuntimeException();
//        }

        outboxRepository.saveAndFlush(outbox);
        return visitEvent;
    }

    @Override
    public List<VisitEvent> GetAllVisitEventsByEntityTypeAndEntityId(int entityType, UUID entityId) {
        return visitEventRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    private Outbox CreateOutboxMessageFromVisitEvent(VisitEvent visitEvent){
        return new Outbox(
                UUID.randomUUID(),
                "visit-event",
                visitEvent.getId(),
                "VisitEventCreated",
                gson.toJson(visitEvent),
                Date.from(Instant.now())
        );
    }
}
