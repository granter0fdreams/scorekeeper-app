package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("host")
    public String displayCreateAccountForm (Model model){
        model.addAttribute("user",new User());
        return "user/host";
    }
    @PostMapping("host")
    public String createAccount(@ModelAttribute("user") User user) {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            return "user/login";
        }
        userRepository.save(user);
        return "redirect:/events/create";
    }
}

