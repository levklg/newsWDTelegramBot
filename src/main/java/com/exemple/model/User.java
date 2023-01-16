package com.exemple.model;


import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @Column(name = "name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "telegram_id")
    private long telegramID;


    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "usersetting_id")
    private UserSetting userSetting;



    public User(){

    }


    public User(String name, String password, long telegramID, UserSetting userSetting) {
        this.userName = name;
        this.password = password;
        this.telegramID = telegramID;
        this.userSetting = userSetting;
    }



    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    @Override
    public User clone() {
        User user = new User(this.userName, this.password, this.telegramID,  this.userSetting);

        return  user;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTelegramID() {
        return telegramID;
    }

    public void setTelegramID(long telegramID) {
        this.telegramID = telegramID;
    }

    public UserSetting getUserSetting() {
        return  userSetting;

    }

    public void setUserSetting(UserSetting userSetting) {
        this.userSetting = userSetting;
    }
}
