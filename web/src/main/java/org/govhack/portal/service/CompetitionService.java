package org.govhack.portal.service;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.CompetitionRepository;
import org.govhack.portal.data.repo.SponsorRepository;
import org.govhack.portal.web.model.SponsorUpdateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional(rollbackFor = Exception.class)
public class CompetitionService {
    private static final Logger LOG = LoggerFactory.getLogger(CompetitionService.class);

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition findOrCreate(String name) {
        return competitionRepository.findByName(name)
                .orElse(competitionRepository.save(new Competition(name)));
    }

}
