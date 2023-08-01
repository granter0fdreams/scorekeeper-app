package org.launchcode.scorekeeperapp.controllers;

import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.launchcode.scorekeeperapp.models.dto.LoginFormDTO;
import org.launchcode.scorekeeperapp.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;
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

    @GetMapping("host")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("user", "Register");
        return "user/host";
    }

    @PostMapping("host")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("user", "Register");
            return "user/host";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("user", "Register");
            return "user/host";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("user", "Register");
            return "user/host";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getEmail(),registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/events/create";
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

        return "redirect:/events/create";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:user/login";
    }

}

