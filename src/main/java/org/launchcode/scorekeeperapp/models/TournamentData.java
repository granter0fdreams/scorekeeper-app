package org.launchcode.scorekeeperapp.models;

import java.util.ArrayList;
import java.util.List;

import static org.aspectj.runtime.internal.Conversions.intValue;

public class TournamentData {

    public static ArrayList<Event> findTournamentByName(String value, Iterable<Event> allTournaments) {

        String lower_val = value.toLowerCase();

        ArrayList<Event> results = new ArrayList<>();

        for (Event tournamentName : allTournaments) {

            if (tournamentName.getTournamentName().toLowerCase().contains(lower_val)) {
                results.add(tournamentName);
            } else {
                System.out.println("No results found");
            }

        }

        return results;
    }

    public List<Event> findTournamentById(int tournamentId, Iterable<Event> allTournaments) {

        int eventId = Event.getId();

        List<Event> results = new ArrayList<>();

        for (Event event : allTournaments) {

            if (intValue(eventId) == intValue(tournamentId)) {
                results.add(event);
            } else {
                System.out.println("No results found");
            }
        }

        return results;

    }
}
