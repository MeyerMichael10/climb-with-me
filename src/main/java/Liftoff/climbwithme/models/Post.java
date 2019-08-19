package Liftoff.climbwithme.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;



    @NotNull
    private String body;


    public Post() {
    }

    public int getId() {
        return id;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
