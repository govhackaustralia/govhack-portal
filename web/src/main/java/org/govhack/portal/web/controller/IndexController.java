package org.govhack.portal.web.controller;

import org.govhack.portal.data.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.govhack.portal.web.model.IndexModel;

@Controller
@Transactional(rollbackFor = Exception.class)
public class IndexController {
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    UserRepository userRepository;
    SponsorRepository sponsorRepository;
    RegionRepository regionRepository;
    PrizeRepository prizeRepository;
    EventRepository eventRepository;
    CompetitionRepository competitionRepository;

    @Autowired
    public IndexController(UserRepository userRepository,
                           SponsorRepository sponsorRepository,
                           RegionRepository regionRepository,
                           PrizeRepository prizeRepository,
                           EventRepository eventRepository,
                           CompetitionRepository competitionRepository) {

        this.userRepository = userRepository;
        this.sponsorRepository = sponsorRepository;
        this.regionRepository = regionRepository;
        this.prizeRepository = prizeRepository;
        this.eventRepository = eventRepository;
        this.competitionRepository = competitionRepository;

    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index", "model", new IndexModel());
    }

}
