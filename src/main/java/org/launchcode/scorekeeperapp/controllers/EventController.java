package org.launchcode.scorekeeperapp.controllers;

import com.google.zxing.WriterException;
import org.launchcode.scorekeeperapp.models.Event;
import org.launchcode.scorekeeperapp.models.QRCodeGenerator;
import org.launchcode.scorekeeperapp.models.Scores;
import org.launchcode.scorekeeperapp.models.User;
import org.launchcode.scorekeeperapp.models.data.EventRepository;
import org.launchcode.scorekeeperapp.models.data.ScoreRepository;
import org.launchcode.scorekeeperapp.models.data.UserRepository;
import org.launchcode.scorekeeperapp.models.dto.userEventScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.springframework.web.util.WebUtils.setSessionAttribute;

@Controller
@Scope("session")
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute(new Event());
        model.addAttribute("title","Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event event, Errors errors, Model model, HttpServletRequest request){
        if (errors.hasErrors()){
            model.addAttribute(new Event());
            model.addAttribute("title","Create Event");
            return "events/create";
        }

        eventRepository.save(event);
        HttpSession session = request.getSession();
        session.setAttribute("event", event.getId());
        //System.out.println("Event ID: " + event.getId());
        return "redirect:/events/play";
    }

    @GetMapping("/index")
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Events");
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("{eventId}")
    public String displayViewEventPage(Model model, @PathVariable int eventId, HttpServletRequest request) {
        String eventLink="https://localhost:8080/events/"+eventId;
        byte[] image = new byte[0];
        try{
            image = QRCodeGenerator.getQRCodeImage(eventLink,250,250);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        String qrcode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("eventLink",eventLink);
        model.addAttribute("qrcode",qrcode);

        HttpSession session = request.getSession();
        session.setAttribute("event", eventId);

        Optional optEvent = eventRepository.findById(eventId);
        ArrayList<Scores> optscores = scoreRepository.findByEventId(eventId);
        HashMap<String, Integer> scoreMap = new HashMap<>();
        for (Scores score : optscores) {
            if (scoreMap.containsKey(score.getUser().getUsername())) {
                scoreMap.put(score.getUser().getUsername(), scoreMap.get(score.getUser().getUsername()) + score.getScore());
            } else
            scoreMap.put(score.getUser().getUsername(), score.getScore());
        }

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(scoreMap.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> sortedScoreMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            sortedScoreMap.put(aa.getKey(), aa.getValue());
        }

        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            model.addAttribute("event", event);
                model.addAttribute("scores", optscores);
                model.addAttribute("scoreMap", sortedScoreMap);

            // Check if the event is closed
            boolean isEventClosed = event.isClosed();
            model.addAttribute("isEventClosed", isEventClosed);

            //Right now its pulling all scores from all events, uncommenting and changing optScore to Scores will revert it once we have user and eventID's attached to scores.
            return "events/view";
        } else {
            return "redirect:../";
            //if the event doesn't exist this redirects you to the main index, prevents 404'ing.
        }
    }


    @GetMapping("/play")
    public String showCreateForm(Model model, HttpServletRequest request) {
        userEventScoreDTO uesdto = new userEventScoreDTO();
        HttpSession session = request.getSession();
        Integer attributeInt = (Integer) session.getAttribute("event");
        Event newEvent = eventRepository.findById(attributeInt).orElse(null);

        for (int i = 1; i <= newEvent.getHoles(); i++) {
            Scores score = new Scores();
            uesdto.addScore(score);
        }
        model.addAttribute("title", "Play Event ${session.getAttribute('event'}");
        model.addAttribute("form", uesdto);
        return "events/play";
    }

    @PostMapping("save")
    public String saveScores(@ModelAttribute userEventScoreDTO dto, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Integer eventId = (Integer) session.getAttribute("event");
        Integer userId = (Integer) session.getAttribute("user");
        String userName = (String) session.getAttribute("userName");
        for (Scores score : dto.getScores()) {
            score.getEvent().setEventId(eventId);
            score.getUser().setUserId(userId);
            score.getUser().setUsername(userName);
        }
        scoreRepository.saveAll(dto.getScores());

        model.addAttribute("scores", scoreRepository.findAll());

        return "redirect:/events/"+eventId; //Temp redirect to index.
    }

    @GetMapping("scoreboard")
    public String displaySingleEventScores(Model model){
        model.addAttribute("title","Event Scores");
        return "events/scoreboard";
    }

    @PostMapping("{eventId}")
    public String closeEvent(@PathVariable int eventId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setClosed(true);
            eventRepository.save(event);
        }
        return "redirect:/user/selectHostOrJoin";
    }


}
