package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Sponsor;
import org.springframework.data.repository.Repository;
import java.util.UUID;

public interface SponsorRepository extends Repository<Sponsor, UUID> {
    Sponsor save(Sponsor entity);
}
