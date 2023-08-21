package org.launchcode.scorekeeperapp.models;

import java.util.*;

import static org.aspectj.runtime.internal.Conversions.intValue;

public class TournamentData {

    private static final Map<Integer, Event> events = new HashMap<>();

    public static ArrayList<Event> getAll() {
        return (ArrayList<Event>) events.values();
    }

    public TournamentData() {
    }

    public static ArrayList<Event> findByTypeAndValue(String category, String value, Iterable<Event> allTournaments) {
        ArrayList<Event> results = new ArrayList();
        if (category.toLowerCase().equals("tournamentName")) {
            results = findTournamentByName(value, allTournaments);
            return results;
        } else if (category.equals("tournamentId")) {
            results = findTournamentById(Integer.parseInt(value), allTournaments);
            return results;
        }
        return results;
    }

    public static ArrayList<Event> findTournamentByName(String value, Iterable<Event> allTournaments) {

        String lower_val = value.toLowerCase();

        ArrayList<Event> results = new ArrayList<>();

        for (Event tournamentName : allTournaments) {
            String eventName = tournamentName.getEventName();
            if (eventName != null && eventName.toLowerCase().contains(lower_val)) {
                results.add(tournamentName);
            } else {
                System.out.println("No results found");
            }

        }

        return results;
    }

    public static ArrayList<User> findUserByUsername(String username, Iterable<User> allUsers){

        String username1 = username.toLowerCase();

        ArrayList<User> results = new ArrayList<>();

        for (User user : allUsers) {

            if (user.getUsername().toLowerCase().contains(username1)) {

                results.add(user);
            }

        }

        return results;

    }

    public static ArrayList<Event> findTournamentById(int tournamentId, Iterable<Event> allTournaments) {

        Event eventInst = new Event();

        ArrayList<Event> results = new ArrayList<>();

        for (Event event : allTournaments) {

            if (intValue(eventInst.getId()) == intValue(tournamentId)) {
                results.add(event);
            }
        }
        return results;
    }
}