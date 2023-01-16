package com.exemple.service;

import java.util.Map;

public class ProcessingMapImp implements ProcessingMap {

    public ProcessingMapImp() {
    }

    @Override
    public boolean compareMaps(Map<String, String> newNews, Map<String, String> oldNews) {
        return false;
    }

    @Override
    public Map<String, String> getMapOfNewsUpdates(Map<String, String> newNews, Map<String, String> oldNews) {
        return null;
    }

    @Override
    public Map<String, String> mergeMapNewsUpdates(Map<String, String> newNews, Map<String, String> oldNews) {
        return null;
    }
}
