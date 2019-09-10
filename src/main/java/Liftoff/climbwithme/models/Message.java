package Liftoff.climbwithme.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Message {

    //id
    @Id
    @GeneratedValue
    private int id;

    private String user;

    //recipient
    @NotNull(message = "Who do you want to send this to?")
    private String recipient;

    //body
    @NotNull(message = "Message can't be blank!")
    private String body;

    public Message() {
    }

    public Message(String recipient) {
        this.recipient = recipient;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
