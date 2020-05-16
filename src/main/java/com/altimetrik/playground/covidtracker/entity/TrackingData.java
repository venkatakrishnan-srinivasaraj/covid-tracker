package com.altimetrik.playground.covidtracker.entity;

import lombok.Data;

@Data
public class TrackingData {
    private String positive;
    private String negative;
    private String death;
    private String totalTestResults;
}
