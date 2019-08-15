package Liftoff.climbwithme.models;

import Liftoff.climbwithme.models.User;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Role {

    @NotNull
    @GeneratedValue
    private Integer roleId;

    @NotNull
    @OneToMany(mappedBy = "role")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
