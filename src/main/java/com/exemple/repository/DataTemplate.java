package com.exemple.repository;

import com.exemple.model.User;
import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DataTemplate<T>  {
    Optional<T> findById(Session session, long id);

    List<T> findAll(Session session);

    void insert(Session session, T object);

    void save(Session session, User user);

    void update(Session session, User user);

    User findUserByName(Session session, String name);
}
