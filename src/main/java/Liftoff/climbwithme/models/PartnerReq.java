package Liftoff.climbwithme.models;

import javax.persistence.Entity;

@Entity
public class PartnerReq {

    private String user;

    private String discipline;

    private String city;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
