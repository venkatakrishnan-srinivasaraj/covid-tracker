package com.altimetrik.playground.covidtracker.controller;

import com.altimetrik.playground.covidtracker.dto.TrackingDataDTO;
import com.altimetrik.playground.covidtracker.entity.TrackingData;
import com.altimetrik.playground.covidtracker.service.CovidTrackerService;
import com.altimetrik.playground.covidtracker.validation.ValidState;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Validated
public class CovidTrackerController {

    private final CovidTrackerService covidTrackerService;
    private final ModelMapper modelMapper;

    @GetMapping("/us/current")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrackingDataDTO getUsTrackingData(){
        TrackingData trackingData = covidTrackerService.getUsTrackingData();
        TrackingDataDTO trackingDataDTO = modelMapper.map(trackingData,TrackingDataDTO.class);
        return trackingDataDTO;
    }

    @GetMapping("/states/{state}/current")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrackingDataDTO getTrackingDataByState(@ValidState @NotBlank @Size(min = 2,max = 2) @PathVariable("state") String state){
        TrackingData trackingData = covidTrackerService.getTrackingDataByState(state);
        TrackingDataDTO trackingDataDTO = modelMapper.map(trackingData,TrackingDataDTO.class);
        return trackingDataDTO;
    }
}
