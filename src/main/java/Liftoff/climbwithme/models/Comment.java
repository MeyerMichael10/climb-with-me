package Liftoff.climbwithme.models;

import Liftoff.climbwithme.models.data.CommentDao;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "parent missing")
    @ManyToOne
    private Post parent;

    private String user;

    @NotNull
    private String body;

    public Comment() {
    }

    public Comment(Post parent) {
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
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

