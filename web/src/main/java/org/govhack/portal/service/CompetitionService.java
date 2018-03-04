package org.govhack.portal.service;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.repo.CompetitionRepository;
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
        Optional<Competition> competition = competitionRepository.findByName(name);
        return competition.orElseGet(() -> competitionRepository.save(new Competition(name)));

    }

}
