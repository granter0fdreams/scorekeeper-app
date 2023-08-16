package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.TournamentData;
import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    private List<Event> events = new ArrayList<>();

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public SearchController() {

    }

    @RequestMapping("")
    public String searchByIdOrName(Model model) {
        model.addAttribute("categories", ListController.searchChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchCategory, @RequestParam String searchTerm) {
        Event eventInst = new Event();
        User userInst = new User();
        ArrayList<Event> tournaments = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        if (searchCategory.equals("tournamentName")) {
            tournaments = TournamentData.findTournamentByName(searchTerm, this.eventRepository.findAll());
        } else if (searchCategory.equals("username")) {
            users = TournamentData.findUserByUsername(searchTerm, this.userRepository.findAll());
        } else {
            System.out.println("No results found");
        }

        model.addAttribute("categories", ListController.searchChoices);
        model.addAttribute("tournaments", tournaments);
        model.addAttribute("users", users);
        return "search";
    }
}
