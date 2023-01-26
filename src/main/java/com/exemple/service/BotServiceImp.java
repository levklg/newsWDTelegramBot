package com.exemple.service;


import com.exemple.model.Bot;
import com.exemple.model.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class BotServiceImp implements BotService {

    CustomerServiceImp customerService;//transactionManager, userDataTemplate);
    private Bot bot;

    public BotServiceImp(Bot bot) {
        customerService = new CustomerServiceImp();
        this.bot = bot;
    }

    @Override
    public Customer login(Customer customer, Message msg) {

        if (msg.hasText()) {
            sendText(customer.getIdChat(), "Придумайте и введите Ваш логин");
        }
        customer.setIsLogin(false);
        customer.setIsLoginInput(true);
        return customer;
    }


    @Override
    public Customer password(Customer customer, Message msg) {

        if (msg.hasText()) {
            sendText(customer.getIdChat(), "Придумайте и введите Ваш пароль");
        }
        customer.setIsPassword(false);
        customer.setIsPasswordInput(true);

       return customer;
    }

    @Override
    public Customer loginInput(Customer customer, Message msg, HashMap<Long,Customer> mapCustomer) {

        if (msg.hasText()) {

             if(!customerService.isUserExists(mapCustomer,customer.getTelegramID(),msg.getText())){
                 customer.setUserName(msg.getText());
                 sendText(customer.getIdChat(), "Ваш логин: " + msg.getText());
                 pause();
                 sendText(customer.getIdChat(), "Далее отправьте команду /password");
                 customer.setIsLoginInput(false);

             }else{
                 sendText(customer.getIdChat(), "Такой логин, уже существует: " + msg.getText());
             }

        }
        return customer;
    }

    @Override
    public Customer passwordInput(Customer customer, Message msg, HashMap<Long,Customer> mapCustomer) {

        if (msg.hasText()) {
            for (Map.Entry<Long, Customer> entry : mapCustomer.entrySet()) {
                long id = entry.getKey();

                if(customer.getIdChat() == id){
                    if(!customer.getUserName().equals("")){
                        String bcryptPassword = new BCryptPasswordEncoder().encode(msg.getText());
                        customer.setPassword(bcryptPassword);
                        sendText(customer.getIdChat(), "Ваш пароль: " + msg.getText());
                        pause();

                        var customerSaved = customerService.saveCustomerToUser(customer);
                        if(customerSaved != null) {
                            sendText(customerSaved.getIdChat(), "Ваша учетная запись успешно создана");
                            pause();
                            sendText(customerSaved.getIdChat(), "Ваш логин: " + customerSaved.getUserName());
                            pause();
                            sendText(customerSaved.getIdChat(), "Ваш пароль: " + msg.getText());
                            pause();
                            sendText(customerSaved.getIdChat(), "Для настроек и запуска пройдите на www.newswatchdog.org");
                        }else {
                            sendText(customer.getIdChat(), "Ошибка при регистрации, повторите регистрацию сначала.");
                        }
                        customer.setIsPasswordInput(false);

                    } else{
                        sendText(customer.getIdChat(), "Сначала выполните команду /login");
                        customer.setIsPasswordInput(false);
                        break;
                    }
                }
            }


        }
       return customer;
    }

    @Override
    public HashMap<Long, Customer> getAllCustomer() {
        var mapCustomer = customerService.getAllCustomer();
        return mapCustomer;
    }


    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            bot.execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }



    public void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
