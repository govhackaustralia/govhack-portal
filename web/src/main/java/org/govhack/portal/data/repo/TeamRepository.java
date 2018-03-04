package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Team;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface TeamRepository extends Repository<Team, UUID> {
    Team save(Team entity);
}
