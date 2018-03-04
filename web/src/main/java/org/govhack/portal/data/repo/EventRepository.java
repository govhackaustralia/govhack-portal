package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends Repository<Event, UUID> {

    @Query("select x from Event x where x.competition = ?1")
    Optional<List<Event>> findAllByCompetition(Competition competition);

    Event save(Event entity);
}
