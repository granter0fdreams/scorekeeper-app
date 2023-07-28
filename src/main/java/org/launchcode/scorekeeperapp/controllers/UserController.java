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
//@RequestMapping("user")
public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("host")
//    public String displayCreateAccountForm (Model model){
//        model.addAttribute("user",new User());
//        return "user/host";
//    }
//    @PostMapping("host")
//    public String createAccount(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
//        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
//            return "user/login";
//        }
//        if (errors.hasErrors()) {
//            model.addAttribute("user", "Create Account");
//            model.addAttribute(new User());
//            return "user/host";
//        }
//        userRepository.save(user);
//        return "events/create";
//    }
}

