package com.exemple.repository;

import com.exemple.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class DataTemplateHibernate<T> implements DataTemplate<T> {

    private final Class<T> clazz;

    public DataTemplateHibernate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<T> findById(Session session, long id) {
        return Optional.ofNullable(session.find(clazz, id));
    }


    @Override
    public List<T> findAll(Session session) {
        return session.createQuery(String.format("from %s", clazz.getSimpleName()), clazz).getResultList();
    }

    @Override
    public void insert(Session session, T object) {
        session.persist(object);
    }

    @Override
    public void save(Session session, User user) {
       session.save(user);

    }

    @Override
    public void update(Session session, User user) {
        session.update(user);
      // session.merge(user).getUserSetting().setAllnews(user.getUserSetting().getAllnews());
      // session.merge(user);

    }

    @Override
    public User findUserByName(Session session,String name) {

        var query =  session.createQuery(String.format("from %s where userName = :name", clazz.getSimpleName()), clazz);
        query.setParameter("name", name);
        var list = query.getResultList();
        var listFindUser = list.stream().findFirst();
        var user = listFindUser.get();
        int i = 0;
        return (User) user;


    }

}
