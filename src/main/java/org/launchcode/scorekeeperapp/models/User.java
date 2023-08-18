package org.launchcode.scorekeeperapp.models;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{

    @OneToMany
    @JoinColumn(name = "userId")
    private List<User> user = new ArrayList<>();

    private Integer userId;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @NotNull
    private String pwHash;
    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;


    private boolean isLoggedIn = false;

    public String getEmail() {
        return email;
    }


    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public User() {
    }

    public User(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }
    public List<User> getUser() {
        return user;
    }

    public void setJobs(List<User> user) {
        this.user = user;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.pwHash = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
