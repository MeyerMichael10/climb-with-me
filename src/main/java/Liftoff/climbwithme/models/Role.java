package Liftoff.climbwithme.models;

import Liftoff.climbwithme.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer roleId;

    private String name;

    @NotNull
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @NotNull
    private String role;

    public Role() {
    }

    public Role(@NotNull String role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
