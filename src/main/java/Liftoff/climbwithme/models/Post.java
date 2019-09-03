package Liftoff.climbwithme.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    private String user;

    @NotNull
    private String body;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments;


    public Post() {
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
