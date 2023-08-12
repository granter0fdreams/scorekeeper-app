package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.launchcode.scorekeeperapp.models.data.ScoreRepository;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.launchcode.scorekeeperapp.models.dto.userEventScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.web.util.WebUtils.setSessionAttribute;

@Controller
@Scope("session")
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
    public String processCreateEventForm(@ModelAttribute @Valid Event event, Errors errors, Model model, HttpServletRequest request){
        if (errors.hasErrors()){
            model.addAttribute(new Event());
            model.addAttribute("title","Create Event");
            return "events/create";
        }
       // userEventScoreDTO uesdto = new userEventScoreDTO();
       // model.addAttribute("form", uesdto);

        eventRepository.save(event);
        HttpSession session = request.getSession();
        session.setAttribute("event", event.getId());
        //System.out.println("Event ID: " + event.getId());
        return "redirect:/events/play";
    }

    @GetMapping("/index")
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }



    @GetMapping("/play")
    public String showCreateForm(Model model, HttpServletRequest request) {
        userEventScoreDTO uesdto = new userEventScoreDTO();
        HttpSession session = request.getSession();
        Integer attributeInt = (Integer) session.getAttribute("event");
        Event newEvent = eventRepository.findById(attributeInt).orElse(null);

        for (int i = 1; i <= newEvent.getHoles(); i++) {
            Scores score = new Scores();
            uesdto.addScore(score);
        }
        model.addAttribute("title", "Play Event ${session.getAttribute('event'}");
        model.addAttribute("form", uesdto);
        return "events/play";
    }

    @PostMapping("save")
    public String saveScores(@ModelAttribute userEventScoreDTO dto, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Integer attributeInt = (Integer) session.getAttribute("event");
        Integer userId = (Integer) session.getAttribute("user");
        for (Scores score : dto.getScores()) {
            score.setEventId(attributeInt);
            score.setUserId(userId);
        }
        scoreRepository.saveAll(dto.getScores());

        model.addAttribute("scores", scoreRepository.findAll());

        return "events/index"; //Temp redirect to index.
    }

    @GetMapping("scoreboard")
    public String displaySingleEventScores(Model model){
        model.addAttribute("title","Event Scores");
        return "events/scoreboard";
    }

//    @GetMapping("scoreboard")
//    public String displaySingleEventScores(@RequestParam Integer eventId, Model model){
//        model.addAttribute("title","Event Scores");
//        model.addAttribute("scores",scoreRepository.findById(eventId));
//        return "events/scoreboard";
//    }


}
