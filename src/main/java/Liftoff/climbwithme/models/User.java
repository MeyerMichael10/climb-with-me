package Liftoff.climbwithme.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Email
    private CharSequence email;

    @NotNull
    @Size(min=3, max=15, message = "Username must be between 3 and 15 characters!")
    private String username;

    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    @NotNull(message = "Passwords do not match!")
    private String verifyPassword;

    public User(String username, CharSequence email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    private void checkPassword() {
        if (this.verifyPassword != null) {
            if (!this.password.equals(this.verifyPassword)) {
                this.setVerifyPassword(null);
            }
        }
    }

    public int getId() {
        return id;
    }

    public CharSequence getEmail() {
        return email;
    }

    public void setEmail(CharSequence email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
