package com.exemple.service;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class ProcessingMapImp implements ProcessingMap {

    public ProcessingMapImp() {
    }

    @Override
    public boolean compareMaps(Map<String, String> newNews, Map<String, String> oldNews) {
        var isSameMaps = oldNews.entrySet().stream()
                .allMatch(e -> e.getKey().equals(newNews.get(e.getKey())));

        return isSameMaps;

    }

    @Override
    public Map<String, String> getMapOfNewsUpdates(Map<String, String> newNews, Map<String, String> oldNews) {
        Map <String, String> updateMapNews = new HashMap<>();

        MapDifference<String, String> diff = Maps.difference(oldNews, newNews);
        updateMapNews = diff.entriesOnlyOnRight();

        return  updateMapNews;

    }

    @Override
    public Map<String, String> mergeMapNewsUpdates(Map<String, String> newNews, Map<String, String> oldNews) {
        Map<String, String> mapMerge = new HashMap<>();
        if(!newNews.isEmpty() || !oldNews.isEmpty()) {
            MapDifference<String, String> diff = Maps.difference(oldNews, newNews);
            var oldMapNews = diff.entriesInCommon();
            var updateMapNews = diff.entriesOnlyOnRight();
            mapMerge.putAll(oldMapNews);
            mapMerge.putAll(updateMapNews);
        }
        return mapMerge;
    }
}
