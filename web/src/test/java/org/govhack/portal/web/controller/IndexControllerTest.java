package org.govhack.portal.web.controller;

import org.govhack.portal.PortalApplication;
import org.govhack.portal.data.model.*;
import org.govhack.portal.data.repo.*;
import org.govhack.portal.service.model.UserRoles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(rollbackFor = Exception.class)
@SpringBootTest(classes = {PortalApplication.class})
@ActiveProfiles("test")
public class IndexControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    PrizeRepository prizeRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    EntryRepository entryRepository;
    @Autowired
    CompetitionRepository competitionRepository;

    @Test
    public void basicDataCreation() {

        User admin = new User("super", "Super Admin", Arrays.asList(UserRoles.ADMIN));
        User organiser = new User("organiser", "Cool Organiser", Arrays.asList(UserRoles.EVENT_ADMIN));
        User sponsorUser = new User("sponsor", "Awesome Sponsor", Arrays.asList(UserRoles.SPONSOR_ADMIN));

        userRepository.save(admin);
        userRepository.save(organiser);
        userRepository.save(sponsorUser);

        Competition comp = new Competition("2018");
        competitionRepository.save(comp);

        Region region = new Region(comp, "Bris");
        regionRepository.save(region);

        Event event = new Event(comp, organiser, region, "John's kitchen");
        eventRepository.save(event);

        Sponsor sponsor = new Sponsor(comp, sponsorUser, "Rich man");
        sponsorRepository.save(sponsor);

        Prize prize1 = new Prize("Knife and fork", comp, sponsor);
        prizeRepository.save(prize1);
        Prize prize2 = new Prize("Full meal!", comp, sponsor);
        prizeRepository.save(prize2);

        Team team = new Team(comp, "My super team!");
        teamRepository.save(team);

        Entry entry = new Entry(comp, "My super team!");
        entry.setPrizes(new HashSet<>(Arrays.asList(prize1)));
        entryRepository.save(entry);

        assertTrue(userRepository.findAll().get().size() == 3);

        List<Competition> savedCompetition = competitionRepository.findAll().get();
        assertTrue(savedCompetition.size() == 1);

        Competition oneComp = savedCompetition.get(0);

        Optional<List<Event>> eventsByCompetition = eventRepository.findAllByCompetition(oneComp);

        assertTrue(eventsByCompetition.get().size() == 1);

        assertTrue(eventsByCompetition.get().get(0).getOwner().equals(organiser));
        assertTrue(eventsByCompetition.get().get(0).getRegion().equals(region));

    }


}