package org.govhack.portal.web.controller;

import org.govhack.portal.data.model.Competition;
import org.govhack.portal.data.model.Prize;
import org.govhack.portal.data.model.Sponsor;
import org.govhack.portal.data.model.User;
import org.govhack.portal.security.Authenticated;
import org.govhack.portal.service.CompetitionService;
import org.govhack.portal.service.PrizeService;
import org.govhack.portal.service.SponsorService;
import org.govhack.portal.web.model.PrizeCreateModel;
import org.govhack.portal.web.model.SponsorUpdateModel;
import org.govhack.portal.web.view.PrizeView;
import org.govhack.portal.web.view.SponsorView;
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
@RequestMapping(value = {"/api/prize"})
public class PrizeApiController {

    @Autowired
    PrizeService prizeService;
    @Autowired
    SponsorService sponsorService;

    @Autowired
    CompetitionService competitionService;

    PrizeApiController() {
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<PrizeView> newPrize(@Authenticated User user, @RequestBody PrizeCreateModel model) {
        Competition competition = competitionService.findOrCreate("2018");
        Sponsor sponsor = sponsorService.findById(model.getSponsor());
        Prize x = prizeService.addPrize(competition, sponsor, model);
        return new ResponseEntity<>(new PrizeView(x), HttpStatus.OK);
    }

}
