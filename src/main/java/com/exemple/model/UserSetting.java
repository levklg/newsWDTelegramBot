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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection(fetch = FetchType.LAZY )
    @CollectionTable(name = "user_setting_mapwebsites" , joinColumns = @JoinColumn(name = "user_setting_id"))
    @MapKeyColumn(name = "map_web_sites_key")
    @Column(name = "map_web_sites")
    private   Map<String, String> mapWebSites = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_setting_list_find_string", joinColumns = @JoinColumn(name = "user_setting_id"))
    @Column( name = "list_find_string" )
    private List<String> listFindString = new ArrayList<>();

    @Column(name = "allnews", updatable = true)
    private String allnews ;

    public UserSetting() {
        mapWebSites.put("rbc","");
        mapWebSites.put("kp40","");
        mapWebSites.put("nikatv","");
        mapWebSites.put("znamkaluga","");
        mapWebSites.put("kaluga-poisk","");
        allnews = "";
    }

    public UserSetting(List<String> listFindString, Map<String, String> mapWebSites, String allnews ){
        this.listFindString = listFindString;
        this.mapWebSites = mapWebSites;
        this.allnews = allnews;

    }

    public  Map<String, String> getMapWebSites() {
        return mapWebSites;
    }

    public  void setMapWebSites(String rbc, String kp40,  String nikatv, String znam, String poisk) {
        mapWebSites.put("rbc",rbc);
        mapWebSites.put("kp40",kp40);
        mapWebSites.put("nikatv",nikatv);
        mapWebSites.put("znamkaluga",znam);
        mapWebSites.put("kaluga-poisk",poisk);
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

    public String getAllnews(){
        return  this.allnews;
    }

    public  void setAllnews(String allnews){
        this.allnews = allnews;
    }

    public UserSetting clone() {
        UserSetting userSetting = new UserSetting(this.listFindString, this.mapWebSites, this.allnews);
        return  userSetting;
    }
}
