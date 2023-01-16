package com.exemple.service;

import com.exemple.model.Customer;
import com.exemple.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    public boolean isUserExists(HashMap<Long, Customer> mapCustomer, long chatID, String name);
    public HashMap<Long, Customer> getAllCustomer();
    HashMap<Long, Customer>createMapCustomer(List<User> userList);
    Customer saveCustomerToUser(Customer customer);
    List<String> getListOfNewsForTheUser(Map<String, String>  mapUpDateNews);



}
