package org.govhack.portal.data.repo;

import org.govhack.portal.data.model.Prize;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface PrizeRepository extends Repository<Prize, UUID> {
    Prize save(Prize entity);
}
