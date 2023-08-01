package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.launchcode.scorekeeperapp.models.data.ScoreRepository;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.launchcode.scorekeeperapp.models.data.userEventScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute(new Event());
        model.addAttribute("title","Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event event, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute(new Event());
            model.addAttribute("title","Create Event");
            return "events/create";
        }
        eventRepository.save(event);
        return "events/create";
    }

    @GetMapping("index")
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }



    @GetMapping("/play")
    public String showCreateForm(Model model) {
        userEventScoreDTO uesdto = new userEventScoreDTO();

        for (int i = 1; i <= 9; i++) {
            uesdto.addScore(new Scores());
        }
        model.addAttribute("form", uesdto);
        return "events/play";
    }

    @PostMapping("save")
    public String saveScores(@ModelAttribute userEventScoreDTO dto, Model model) {
        scoreRepository.saveAll(dto.getScores());

        model.addAttribute("scores", scoreRepository.findAll());
        return "events/index"; //Temp redirect to index.
    }


}