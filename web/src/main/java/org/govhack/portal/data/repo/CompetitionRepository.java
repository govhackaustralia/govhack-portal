package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Competition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends Repository<Competition, UUID> {

    @Query("select x from Competition x")
    Optional<List<Competition>> findAll();

    Competition save(Competition entity);
}
