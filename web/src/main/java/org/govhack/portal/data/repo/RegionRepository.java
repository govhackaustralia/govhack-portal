package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Region;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface RegionRepository extends Repository<Region, UUID> {
    Region save(Region entity);
}
