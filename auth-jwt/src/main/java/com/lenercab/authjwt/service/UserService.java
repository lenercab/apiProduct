package com.lenercab.authjwt.service;



import com.lenercab.authjwt.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findByUsername(String username);

    List<User> findAllUsers();
}