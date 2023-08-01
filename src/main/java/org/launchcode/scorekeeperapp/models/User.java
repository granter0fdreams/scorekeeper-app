package org.launchcode.scorekeeperapp.models;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @NotNull
    private String pwHash;
    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

//    @NotBlank
//    @Size(min = 6, message = "Password must be at least 6 characters long")
//    private String password;

    private boolean isLoggedIn = false;

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.pwHash = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
