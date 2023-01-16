package com.exemple.service;

import com.exemple.model.Customer;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

public interface BotService {
   Customer login(Customer customer, Message msg);
   Customer  password(Customer customer, Message msg);
   Customer loginInput(Customer customer, Message msg, HashMap<Long,Customer> mapCustomer);
   Customer passwordInput(Customer customer, Message msg, HashMap<Long,Customer> mapCustomer);
   public HashMap<Long, Customer> getAllCustomer();

}
