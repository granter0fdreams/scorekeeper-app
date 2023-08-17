package org.launchcode.scorekeeperapp.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.TournamentData;
import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.ScoreRepository;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.launchcode.scorekeeperapp.models.dto.LoginFormDTO;
import org.launchcode.scorekeeperapp.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;
    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("user", "Register");
        return "user/register";
    }

    @PostMapping("register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("user", "Register");
            return "user/register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("user", "Register");
            return "user/register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("user", "Register");
            return "user/register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getEmail(),registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        HttpSession session = request.getSession();
        session.setAttribute("user", newUser.getId());
        session.setAttribute("username", newUser.getUsername());

        return "redirect:/user/selectHostOrJoin";
    }

    @GetMapping("selectHostOrJoin")
    public String displayDashboardPage(Model model){
        model.addAttribute("title", "Please select host or join.");
        return "user/selectHostOrJoin";
    }

    @GetMapping("login")
    public String displayLoginForm(Model model) {
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        model.addAttribute("user", "Log In");
        return "user/login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("user", "Log In");
            return "user/login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("user", "Log In");
            return "user/login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("user", "Log In");
            return "user/login";
        }


        setUserInSession(request.getSession(), theUser);
        HttpSession session = request.getSession();
        session.setAttribute("user", theUser.getId());
        session.setAttribute("userName", theUser.getUsername());
        //System.out.println(theUser.getUsername());


        return "redirect:/user/selectHostOrJoin";
    }



    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/user/login";
    }

}

