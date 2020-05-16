package com.altimetrik.playground.covidtracker.serviceimpl;

import com.altimetrik.playground.covidtracker.config.CovidTrackerApiConfig;
import com.altimetrik.playground.covidtracker.entity.TrackingData;
import com.altimetrik.playground.covidtracker.service.CovidTrackerApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class CovidTrackerApiServiceImpl implements CovidTrackerApiService {
    private final RestTemplate restTemplate;
    private final CovidTrackerApiConfig covidTrackerApiConfig;
    private String covidTrackerApiStateUrl;
    private String covidTrackerApiUsUrl;

    public CovidTrackerApiServiceImpl(RestTemplate restTemplate, CovidTrackerApiConfig covidTrackerApiConfig) {
        this.restTemplate = restTemplate;
        this.covidTrackerApiConfig = covidTrackerApiConfig;
    }

    @PostConstruct
    public void init() {
        covidTrackerApiUsUrl = new StringBuilder(covidTrackerApiConfig.getBaseUrl()).append(covidTrackerApiConfig.getUs()).toString();
        covidTrackerApiStateUrl = new StringBuilder(covidTrackerApiConfig.getBaseUrl()).append(covidTrackerApiConfig.getState()).toString();
    }

    @Override
    public TrackingData getUsTrackingData() {
        TrackingData[] trackingData = restTemplate.getForEntity(covidTrackerApiUsUrl, TrackingData[].class).getBody();
        return trackingData[0];
    }

    @Override
    public TrackingData getTrackingDataByState(String state) {
        Map<String, String> pathParameter = new HashMap<>();
        pathParameter.put("state", state);
        URI covidTrackerApiStateUrlWithStateParameter = UriComponentsBuilder.fromUriString(covidTrackerApiStateUrl)
                .buildAndExpand(pathParameter).toUri();
        TrackingData trackingData = restTemplate.getForEntity(covidTrackerApiStateUrlWithStateParameter, TrackingData.class).getBody();
        return trackingData;
    }
}
