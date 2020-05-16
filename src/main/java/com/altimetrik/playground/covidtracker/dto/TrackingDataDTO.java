package com.altimetrik.playground.covidtracker.dto;

import lombok.Data;

@Data
public class TrackingDataDTO {
    private String positive;
    private String negative;
    private String death;
    private String totalTestResults;
}
