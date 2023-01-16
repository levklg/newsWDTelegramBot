package com.exemple.service;

import com.exemple.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    User saveUser(User user);

    Optional<User> getUser(long id);

    List<User> findAll();
}