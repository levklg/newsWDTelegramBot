package com.exemple.model;

public class Customer {
    private long idChat;
    private  User user;

    private boolean isLogin = false;
    private boolean isLoginInput = false;
    private boolean isPassword = false;
    private boolean isPasswordInput = false;
    private boolean registration = false;


    public Customer(long idChat) {
        user = new User();
        user.setUserSetting(new UserSetting());
        this.idChat = idChat;
        user.setTelegramID(idChat);
    }

    public Customer(User user) {
        this.user = user;

    }



    public long getIdChat() {
        return user.getTelegramID();
    }

    public void setIdChat(long idChat) {
        user.setTelegramID(idChat);
    }

    public String getUserName(){
        return user.getUserName();
    }

    public void setUserName(String login){

        user.setUserName(login);
    }

    public void setPassword(String password){

        user.setPassword(password);
    }
    public String getPassword(){
       return this.user.getPassword();
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLoginInput() {
        return isLoginInput;
    }

    public void setIsLoginInput(boolean loginInput) {
        isLoginInput = loginInput;
    }

    public boolean isPassword() {
        return isPassword;
    }

    public void setIsPassword(boolean password) {
        isPassword = password;
    }

    public boolean isPasswordInput() {
        return isPasswordInput;
    }

    public void setIsPasswordInput(boolean passwordInput) {
        isPasswordInput = passwordInput;
    }

    public User getUser(){
        return this.user;
    }

    public long getTelegramID(){
        return user.getTelegramID();
    }


}
