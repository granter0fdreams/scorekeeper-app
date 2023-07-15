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
@RequestMapping("welcome")
public class WelcomeController {
    @RequestMapping("")
    public String index(Model model) {
        return "welcome";
    }

}
