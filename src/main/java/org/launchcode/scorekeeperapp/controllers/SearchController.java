package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    @Autowired
    private EventRepository eventRepository;
}
