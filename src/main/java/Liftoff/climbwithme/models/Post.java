package Liftoff.climbwithme.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @NotNull
    private String body;

    public Post(User user, String body) {
        this.user = user;
        this.body = body;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
