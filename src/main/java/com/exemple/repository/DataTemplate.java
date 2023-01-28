package com.exemple.repository;

import com.exemple.model.User;
import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DataTemplate<T>  {
    Optional<T> findById(Session session, long id);

   // List<T> findByEntityField(Session session, String entityFieldName, Object entityFieldValue);

    List<T> findAll(Session session);

    void insert(Session session, T object);

    void update(Session session, T object);

    User findUserByName(Session session, String name);
}
