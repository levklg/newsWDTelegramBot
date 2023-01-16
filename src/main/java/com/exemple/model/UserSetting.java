package com.exemple.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "usersettings")
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;



    @ElementCollection(fetch = FetchType.LAZY )
    @CollectionTable(name = "user_setting_list_find_string", joinColumns = @JoinColumn(name = "user_setting_id"))
    @MapKeyColumn(name = "map_web_sites_key")
    @Column(name = "map_web_sites")
    private   Map<String, String> mapWebSites = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_setting_mapwebsites", joinColumns = @JoinColumn(name = "user_setting_id"))
    @Column( name = "list_find_string" )
    private List<String> listFindString = new ArrayList<>();


    public UserSetting() {
        mapWebSites.put("https://www.rbc.ru/","");
       mapWebSites.put("https://www.kp40.ru/","");
        mapWebSites.put("https://kaluga24.tv/home/news","");
        mapWebSites.put("https://nikatv.ru/news/lenta","");

        listFindString.add("Hello");
    }

    public UserSetting(List<String> listFindString, Map<String, String> mapWebSites ){
        this.listFindString = listFindString;
        this.mapWebSites = mapWebSites;

    }

    public  Map<String, String> getMapWebSites() {
        return mapWebSites;
    }

    public  void setMapWebSites(String rbc, String kp40, String kaluga24, String nikatv) {
        mapWebSites.put("https://www.rbc.ru/",rbc);
        mapWebSites.put("https://www.kp40.ru/",kp40);
        mapWebSites.put("https://kaluga24.tv/home/news",kaluga24);
        mapWebSites.put("https://nikatv.ru/news/lenta",nikatv);
    }

    public  List<String> getListFindString() {
        return listFindString;
    }

    public  void setListFindString(List<String> listFindString) {
        this.listFindString = listFindString;
    }

    public long getId() {
        return id;
    }

    public UserSetting clone() {
        UserSetting userSetting = new UserSetting(this.listFindString, this.mapWebSites);

        return  userSetting;
    }
}
