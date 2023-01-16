package com.exemple.dao;

import java.util.HashMap;
import java.util.Map;

public class NewsStorageContainer {
  private static final    Map<String,String> mapKP40 = new HashMap<>();
  private static final    Map<String,String> mapNikaTV  = new HashMap<>();
  private static final    Map<String,String> mapRBC  = new HashMap<>();
  private static final    Map<String,String> mapKalugaPoisk  = new HashMap<>();
  private static final    Map<String,String> mapZnamKaluga  = new HashMap<>();


    private static final    Map<String,String> mapNowKP40 = new HashMap<>();
    private static final    Map<String,String> mapNowNikaTV  = new HashMap<>();
    private static final    Map<String,String> mapNowRBC  = new HashMap<>();
    private static final    Map<String,String> mapNowKalugaPoisk  = new HashMap<>();
    private static final    Map<String,String> mapNowZnamKaluga  = new HashMap<>();



    public NewsStorageContainer() {

    }
    public void setMapKP40(Map<String,String> mapNews){
       mapKP40.putAll(mapNews);
    }

   public void setMapNikaTV(Map<String,String> mapNews){
        mapNikaTV.putAll(mapNews);
    }

    public void setMapRBC(Map<String,String> mapNews){
        mapRBC.putAll(mapNews);
    }

   public void setMapKalugaPoisk(Map<String,String> mapNews){
        mapKalugaPoisk.putAll(mapNews);
    }

   public void setMapZnamKaluga(Map<String,String> mapNews){mapZnamKaluga.putAll(mapNews); }

    public void setMapNowKP40(Map<String,String> mapNews){
        mapNowKP40.putAll(mapNews);
    }

    public void setMapNowNikaTV(Map<String,String> mapNews){
        mapNowNikaTV.putAll(mapNews);
    }

    public void setMapNowRBC(Map<String,String> mapNews){
        mapNowRBC.putAll(mapNews);
    }

    public void setMapNowKalugaPoisk(Map<String,String> mapNews){
        mapNowKalugaPoisk.putAll(mapNews);
    }

    public void setMapNowZnamKaluga(Map<String,String> mapNews){
        mapNowZnamKaluga.putAll(mapNews);
    }



   public   Map<String, String> getNewsKP40(){
        return mapKP40;
     }

   public Map<String, String> getNewsNikaTV(){
        return mapNikaTV;
    }

   public Map<String, String> getNewsRBC(){
        return mapRBC;
    }

   public Map<String, String> getNewsKalugaPoisk(){
        return mapKalugaPoisk;
    }

   public Map<String, String> getNewsZnamKaluga(){
        return mapZnamKaluga;
    }

    public   Map<String, String> getNowNewsKP40(){
        return mapNowKP40;
    }

    public Map<String, String> getNowNewsNikaTV(){
        return mapNowNikaTV;
    }

    public Map<String, String> getNowNewsRBC(){
        return mapNowRBC;
    }

    public Map<String, String> getNowNewsKalugaPoisk(){
        return mapNowKalugaPoisk;
    }

    public Map<String, String> getNowNewsZnamKaluga(){
        return mapNowZnamKaluga;
    }








}
