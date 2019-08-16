package Liftoff.climbwithme.models;

import Liftoff.climbwithme.models.User;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class Role {

    @NotNull
    @GeneratedValue
    private Integer roleId;

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
}
