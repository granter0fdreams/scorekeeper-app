package org.launchcode.scorekeeperapp.Controllers;


import org.launchcode.scorekeeperapp.Models.Data.TestRepo;
import org.launchcode.scorekeeperapp.Models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventsController {

    @Autowired
    private TestRepo testRepo;

    @GetMapping("")
    public String index(Model model){
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Current Events");
        model.addAttribute(new Event());
        return "events/create";
    }
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/error";
        }
        testRepo.save(newEvent);
        return "events/index";  //change this to send the user to the newly created event page.
    }

}
