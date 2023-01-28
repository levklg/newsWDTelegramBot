package com.exemple.service;

import com.exemple.model.Customer;
import com.exemple.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.exemple.App.transactionManager;
import static com.exemple.App.userDataTemplate;

public class CustomerServiceImp implements CustomerService {

    private static DBServiceUser dbServiceUser;



    public CustomerServiceImp() {

        dbServiceUser = new DbServiceUserImpl(transactionManager, userDataTemplate);

    }

    @Override
    public boolean isUserExists(HashMap<Long, Customer> mapCustomer,long chatID, String name){
        var isFindUser = mapCustomer.entrySet().stream().filter(e -> e.getValue().getUserName() == name
                && e.getValue().getTelegramID() == chatID ).findFirst().isEmpty();
        if(isFindUser){
            return  false;   //если совпадения не найдены возвращаем
        }else{
            return true;  //  mapCustomer не пустой, значит совпадения найдены, возвращаем true
        }

    }

    @Override
    public HashMap<Long, Customer> getAllCustomer() {
       var listUsers = dbServiceUser.findAll();
       var mapCustomer = createMapCustomer(listUsers);
        return mapCustomer;
    }

    @Override
    public User findCustomerName(String name) {
        var user = dbServiceUser.findByUserName(name);
        return  user;
    }

    @Override
    public void updateUser(User user) {
        dbServiceUser.update(user);
    }


    @Override
    public HashMap<Long, Customer> createMapCustomer(List<User> userList) {
        HashMap<Long, Customer> mapCustomer  = new HashMap<>();
        for(var user : userList){
            Customer newCustomer = new Customer(user);
            mapCustomer.put(newCustomer.getIdChat(),newCustomer);
        }
        return  mapCustomer;
    }

    @Override
    public Customer saveCustomerToUser(Customer customer) {

       var user = dbServiceUser.saveUser(customer.getUser());
       if(user != null){
           return customer;
       }else {
           return null;
       }
    }

    @Override
    public List<String> getListOfNewsForTheUser(Map<String, String> mapUpDateNews) {


        return null;
    }




}
