package org.govhack.portal.web.controller;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.UserRepository;
import org.govhack.portal.security.Authenticated;
import org.govhack.portal.service.CompetitionService;
import org.govhack.portal.service.SponsorService;
import org.govhack.portal.service.UserService;
import org.govhack.portal.web.model.SponsorUpdateModel;
import org.govhack.portal.web.view.SponsorView;
import org.govhack.portal.service.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping(value = {"/api/sponsor"})
public class SponsorApiController {

    @Autowired
    SponsorService sponsorService;

    @Autowired
    CompetitionService competitionService;

    SponsorApiController() {
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SponsorView> updateSponsor(@Authenticated User user, @RequestBody SponsorUpdateModel model) {
        Competition competition = competitionService.findOrCreate("2018");
        Sponsor x = sponsorService.updateOrCreate(competition, user, model);
        return new ResponseEntity<>(new SponsorView(x), HttpStatus.OK);
    }

}
