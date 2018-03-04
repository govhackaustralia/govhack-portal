package org.govhack.portal.service;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.SponsorRepository;
import org.govhack.portal.web.model.SponsorUpdateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional(rollbackFor = Exception.class)
public class SponsorService {
    private static final Logger LOG = LoggerFactory.getLogger(SponsorService.class);

    private final SponsorRepository sponsorRepository;

    @Autowired
    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    public Sponsor findById(UUID id) {
        return sponsorRepository.findById(id).get();
    }

    public Sponsor findOneByOwner(User user) {
        Optional<Sponsor> oneByUser = sponsorRepository.findOneByUser(user);
        return oneByUser.orElse(new Sponsor());
    }

    public Sponsor updateOrCreate(Competition competition, User owner, SponsorUpdateModel model) {

        Optional<Sponsor> s = sponsorRepository.findOneByUser(owner);
        Sponsor sponsor;
        if (s.isPresent()) {
            sponsor = s.get();
            sponsor.setName(model.getName());
            sponsorRepository.save(sponsor);
        } else {
            sponsor = sponsorRepository.save(new Sponsor(competition, owner, model.getName()));
        }
        return sponsor;
    }

}
