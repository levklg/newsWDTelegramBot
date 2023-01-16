package com.exemple.service;




import com.exemple.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exemple.repository.DataTemplate;
import com.exemple.sessionmanager.TransactionManager;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceUserImpl implements DBServiceUser {
    private static final Logger log = LoggerFactory.getLogger(DbServiceUserImpl.class);


    private final TransactionManager transactionManager;


    private final DataTemplate<User> userDataTemplate;


    public DbServiceUserImpl(TransactionManager transactionManager, DataTemplate<User> userDataTemplate) {
        this.transactionManager = transactionManager;
       this.userDataTemplate = userDataTemplate;
    }


    @Override
    public User saveUser(User user) {
        return transactionManager.doInTransaction(session -> {
            var userCloned = user.clone();
            if (user.getId() == null) {
                userDataTemplate.insert(session, userCloned);
                log.info("created client: {}", userCloned);
                return userCloned;
            }
            userDataTemplate.update(session, userCloned);
            log.info("updated client: {}", userCloned);
            return userCloned;
        });
    }

    @Override
    public Optional<User> getUser(long id) {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var clientOptional = userDataTemplate.findById(session, id);
            log.info("client: {}", clientOptional);
            return clientOptional;
        });
    }

    @Override
    public List<User> findAll() {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var userList = userDataTemplate.findAll(session);
            log.info("userList:{}", userList);
            return userList;
        });
    }


}