package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.TournamentData;
import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.launchcode.scorekeeperapp.models.data.ScoreRepository;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class ListController {

        @Autowired
        private EventRepository eventRepository;
        @Autowired
        private ScoreRepository scoreRepository;
        @Autowired
        private UserRepository userRepository;
        static HashMap<String, String> searchChoices = new HashMap();

    public ListController() {
        searchChoices.put("all", "All");
        searchChoices.put("tournamentName", "Tournament Name");
        searchChoices.put("eventId", "Tournament Id");
    }

        @RequestMapping({""})
        public String listTournaments(Model model) {
        model.addAttribute("title", "Tournament List");
        model.addAttribute("tournaments", this.eventRepository.findAll());
        return "list";
    }

        @RequestMapping({"tournaments"})
        public String listTournamentsByNameAndId(Model model, @RequestParam String category, @RequestParam String value) {
        Object tournaments = null;
        if (category.toLowerCase().equals("tournamentName")) {
            tournaments = TournamentData.findTournamentByName(value, this.eventRepository.findAll());
            model.addAttribute("title", "Tournament Results");
        } else if (category.toLowerCase().equals("eventId")){
            tournaments = TournamentData.findByTypeAndValue(category, value, this.eventRepository.findAll());
            model.addAttribute("title", "Tournament Results");
        }
        model.addAttribute("tournaments", tournaments);
        return "list-tournaments";
    }
    }
