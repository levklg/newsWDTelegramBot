package com.exemple;

import com.exemple.dbmigrations.MigrationsExecutorFlyway;
import com.exemple.model.User;
import com.exemple.model.UserSetting;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.exemple.repository.DataTemplate;
import com.exemple.repository.DataTemplateHibernate;
import com.exemple.repository.HibernateUtils;
import com.exemple.sessionmanager.TransactionManager;
import com.exemple.sessionmanager.TransactionManagerHibernate;

public class App {

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    public static TransactionManager transactionManager;
    public static DataTemplate<User> userDataTemplate;
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws TelegramApiException {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);


        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class, UserSetting.class);

         transactionManager = new TransactionManagerHibernate(sessionFactory);

        userDataTemplate = new DataTemplateHibernate<>(User.class);




///
    //    var dbServiceUser = new DbServiceUserImpl( transactionManager,userDataTemplate );
    //    var user = new User("Lev","admin", 0,new UserSetting());
    //    dbServiceUser.saveUser(user);
     //  var users = dbServiceUser.findAll();

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(new Bot());
      //  bot.sendText(1234L, "Hello World!");

    }


}
