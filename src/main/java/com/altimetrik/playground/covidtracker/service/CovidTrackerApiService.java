package com.altimetrik.playground.covidtracker.service;

import com.altimetrik.playground.covidtracker.entity.TrackingData;

public interface CovidTrackerApiService {
    TrackingData getUsTrackingData();
    TrackingData getTrackingDataByState(String state);
}
