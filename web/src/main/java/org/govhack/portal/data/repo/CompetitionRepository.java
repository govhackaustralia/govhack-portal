package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Competition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends Repository<Competition, UUID> {

    @Query("select x from Competition x")
    Optional<List<Competition>> findAll();

    Competition save(Competition entity);

    @Query("select x from Competition x where x.name = ?1")
    Optional<Competition> findByName(String name);
}
