package org.govhack.portal.service;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Prize;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.repo.PrizeRepository;
import org.govhack.portal.web.model.PrizeCreateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
public class PrizeService {
    private static final Logger LOG = LoggerFactory.getLogger(PrizeService.class);

    private final PrizeRepository prizeRepository;

    @Autowired
    public PrizeService(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }

    public Prize addPrize(Competition competition, Sponsor sponsor, PrizeCreateModel model) {
        Prize prize = new Prize(model.getName(), competition, sponsor);
        return prizeRepository.save(prize);
    }

}
