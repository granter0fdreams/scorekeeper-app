package org.launchcode.scorekeeperapp.controllers;


import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.launchcode.scorekeeperapp.models.Event;
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

    @GetMapping("")
    public String index(Model model){
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        //model.addAttribute("title", "Current Events");
        model.addAttribute(new Event());
        return "events/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "events/index";  //change this to send the user to the newly created event page.
    }

}