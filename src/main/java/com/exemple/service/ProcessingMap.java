package com.exemple.service;

import java.util.Map;

public interface ProcessingMap {
    boolean compareMaps(Map<String, String> newNews, Map<String, String> oldNews );
    Map<String, String> getMapOfNewsUpdates(Map<String, String> newNews, Map<String, String> oldNews );
    Map<String,String> mergeMapNewsUpdates(Map<String, String> newNews, Map<String, String> oldNews);
}
