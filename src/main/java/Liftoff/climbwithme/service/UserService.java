package Liftoff.climbwithme.service;

import Liftoff.climbwithme.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
