package org.launchcode.scorekeeperapp.models;

import javax.persistence.Entity;

@Entity
public class Scores extends AbstractEntity{

        private Integer userId;
        private Integer eventId;
        private Integer position;
        private Integer score;

        public Scores (Integer userId, Integer eventId, Integer position, Integer score){
            this.userId = userId;
            this.eventId = eventId;
            this.position = position;
            this.score = score;
        }
        public Scores(){}

        public Integer getUserId() {
                return userId;
        }

        public void setUserId(Integer userId) {
                this.userId = userId;
        }

        public Integer getEventId() {
                return eventId;
        }

        public void setEventId(Integer eventId) {
                this.eventId = eventId;
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
