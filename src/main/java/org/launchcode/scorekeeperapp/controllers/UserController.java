package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("create-account")
    public String displayCreateAccountForm (Model model){
        model.addAttribute("user",new User());
        return "create_account";
    }
    @PostMapping("create-account")
    public String createAccount(@ModelAttribute("user") User user) {
        if (userRepository.existsById(user.getUsername()) || userRepository.existsById(user.getEmail())) {
            return "login";
        }
        userRepository.save(user);
        return "redirect:/create_tournament";
    }
}

