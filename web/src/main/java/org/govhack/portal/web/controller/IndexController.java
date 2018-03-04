package org.govhack.portal.web.controller;

import org.govhack.portal.security.Authenticated;
import org.govhack.portal.data.model.User;
import org.govhack.portal.data.repo.*;
import org.govhack.portal.service.view.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = {"/*"}, method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index", "model", new IndexModel());
    }

    @RequestMapping(value = {"/login-success"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<UserView> loginSuccess(@Authenticated User user) {
        return new ResponseEntity<>(new UserView(user), HttpStatus.OK);
    }

    @RequestMapping(value = {"/loginError"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> loginError() {
        return new ResponseEntity<>("ERROR", HttpStatus.UNAUTHORIZED);
    }

}
