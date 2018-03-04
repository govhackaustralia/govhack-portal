package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface SponsorRepository extends Repository<Sponsor, UUID> {
    Sponsor save(Sponsor entity);

    @Query("select x from Sponsor x where x.owner = ?1")
    Optional<Sponsor> findOneByUser(User owner);
}
