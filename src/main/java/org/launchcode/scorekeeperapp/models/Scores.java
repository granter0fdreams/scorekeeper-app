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
//        private Integer userId;
//        private Integer eventId;
        @ManyToOne
        private User user;
        @ManyToMany
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

        //        public String getUserName() {
//                return userName;
//        }

//        public void setUserName(String userName) {
//                this.userName = userName;
//        }
//
//        private String userName;

//        public Scores (Integer userId, Integer eventId, Integer position, Integer score){
//            this.userId = userId;
//            this.eventId = eventId;
//            this.position = position;
//            this.score = score;
//        }
//        public Scores(){}

//        public Integer getUserId() {
//                return userId;
//        }
//
//        public void setUserId(Integer userId) {
//                this.userId = userId;
//        }
//
//        public Integer getEventId() {
//                return eventId;
//        }
//
//        public void setEventId(Integer eventId) {
//                this.eventId = eventId;
//        }

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
