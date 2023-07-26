package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    public String createAccount(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
        return "user/host";
    }

    @GetMapping("login")
    public String displayLoginForm(Model model){
        model.addAttribute("user",new User());
        return "user/login";
    }
    @PostMapping("login")
    public String processLoginForm(@ModelAttribute("user") User user, Errors errors, Model model){
        if (userRepository.existsById(user.getId()) && user.getPassword() == userRepository.findById(user.getId()).get().getPassword()){
            return "events/join";
        }
        if (errors.hasErrors()){
            model.addAttribute("user", "Login");
            model.addAttribute(new User());
            return "user/login";
        }
        return "events/join";
    }

    @GetMapping("register")
    public String displayRegisterForm(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }

    @PostMapping("register")
    public String processRegisterForm(@ModelAttribute("user") User user, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("user", "Login");
            model.addAttribute(new User());
            return "user/login";
        }
        if (userRepository.existsById(user.getId()) && user.getPassword() == userRepository.findById(user.getId()).get().getPassword()) {
            user.setLoggedIn(true);
            model.addAttribute("user", user);
        }
            return "events/join";

    }

}

