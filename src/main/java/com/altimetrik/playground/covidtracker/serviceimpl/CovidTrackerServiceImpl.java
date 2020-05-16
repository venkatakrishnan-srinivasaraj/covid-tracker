package com.altimetrik.playground.covidtracker.serviceimpl;

import com.altimetrik.playground.covidtracker.entity.TrackingData;
import com.altimetrik.playground.covidtracker.service.CovidTrackerApiService;
import com.altimetrik.playground.covidtracker.service.CovidTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CovidTrackerServiceImpl implements CovidTrackerService {

    private final CovidTrackerApiService covidTrackerApiService;

    @Override
    public TrackingData getUsTrackingData() {
        return covidTrackerApiService.getUsTrackingData();
    }

    @Override
    public TrackingData getTrackingDataByState(String state) {
        return covidTrackerApiService.getTrackingDataByState(state);
    }
}
