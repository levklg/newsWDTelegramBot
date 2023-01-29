package com.exemple.service;

import com.exemple.model.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    User saveUser(User user);

    Optional<User> getUser(long id);

    List<User> findAll();

    void update(User user);

    User findByUserName(String name);
}