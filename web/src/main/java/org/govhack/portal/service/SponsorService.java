package org.govhack.portal.service;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.SponsorRepository;
import org.govhack.portal.data.repo.UserRepository;
import org.govhack.portal.service.model.UserRoles;
import org.govhack.portal.web.model.SponsorUpdateModel;
import org.govhack.portal.web.model.UserCreateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(rollbackFor = Exception.class)
public class SponsorService {
    private static final Logger LOG = LoggerFactory.getLogger(SponsorService.class);

    private final SponsorRepository sponsorRepository;

    @Autowired
    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    public Sponsor updateOrCreate(Competition competition, User owner, SponsorUpdateModel sponsor) {

        Sponsor s = sponsorRepository.findOneByUser(owner)
                .orElse(sponsorRepository.save(new Sponsor(competition, owner, sponsor.getName())));

        s.setName(sponsor.getName());

        return sponsorRepository.save(s);
    }

}
