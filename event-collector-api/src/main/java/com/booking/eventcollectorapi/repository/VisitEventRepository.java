package com.booking.eventcollectorapi.repository;

import com.booking.eventcollectorapi.models.Entity.VisitEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VisitEventRepository extends JpaRepository<VisitEvent, UUID> {
    List<VisitEvent> findByEntityTypeAndEntityId(int entityType, UUID entityId);
}
