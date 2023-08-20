package org.launchcode.scorekeeperapp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Scores extends AbstractEntity{

        @ManyToMany(mappedBy="scores")
        private final List<Event> events = new ArrayList<>();
        @ManyToOne
        private User user;
        @ManyToOne
        private Event event;
        private Integer position;
        private Integer score;

        public Scores(){}

        public Scores(User user, Event event, Integer position, Integer score) {
                super();
                this.user = user;
                this.event = event;
                this.position = position;
                this.score = score;
        }

        public List<Event> getEvents() {
                return events;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Event getEvent() {
                return event;
        }

        public void setEvent(Event event) {
                this.event = event;
        }


        public Integer getPosition() {
                return position;
        }

        public void setPosition(Integer position) {
                this.position = position;
        }

        public Integer getScore() {
                return score;
        }

        public void setScore(Integer score) {
                this.score = score;
        }
}
