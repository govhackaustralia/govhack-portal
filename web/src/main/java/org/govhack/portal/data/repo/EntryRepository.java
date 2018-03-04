package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Entry;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface EntryRepository extends Repository<Entry, UUID> {
    Entry save(Entry entity);
}
