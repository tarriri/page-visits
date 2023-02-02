package com.booking.eventcollectorapi.repository;

import com.booking.eventcollectorapi.models.Entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

}
