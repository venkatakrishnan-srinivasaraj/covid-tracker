package com.altimetrik.playground.covidtracker.controller;

import com.altimetrik.playground.covidtracker.entity.TrackingData;
import com.altimetrik.playground.covidtracker.service.CovidTrackerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CovidTrackerControllerTest{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CovidTrackerService covidTrackerService;

    TrackingData mockedTrackingData(){
        TrackingData trackingData = new TrackingData();
        trackingData.setPositive("1");
        trackingData.setNegative("2");
        trackingData.setDeath("3");
        trackingData.setTotalTestResults("4");
        return trackingData;
    }

    @Test
    void getUsTrackingDataShouldReturn202() throws Exception {
        Mockito.when(covidTrackerService.getUsTrackingData()).thenReturn(mockedTrackingData());
        this.mockMvc.perform(get("/api/v1/us/current"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.positive").value("1"))
                .andExpect(jsonPath("$.negative").value("2"))
                .andExpect(jsonPath("$.death").value("3"))
                .andExpect(jsonPath("$.totalTestResults").value("4"));
    }

    @Test
    void getTrackingDataByStateShouldReturn202() throws Exception {
        Mockito.when(covidTrackerService.getTrackingDataByState(Mockito.anyString())).thenReturn(mockedTrackingData());
        this.mockMvc.perform(get("/api/v1/states/TX/current"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.positive").value("1"))
                .andExpect(jsonPath("$.negative").value("2"))
                .andExpect(jsonPath("$.death").value("3"))
                .andExpect(jsonPath("$.totalTestResults").value("4"));
    }

    @Test
    void getTrackingDataByStateWithLessThan2CharactersShouldReturn400() throws Exception {
        this.mockMvc.perform(get("/api/v1/states/T/current"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value(containsString("size must be between 2 and 2")));
    }

    @Test
    void getTrackingDataByStateWithMoreThan2CharactersShouldReturn400() throws Exception {
        this.mockMvc.perform(get("/api/v1/states/TXS/current"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value(containsString("size must be between 2 and 2")));
    }

    @Test
    void getTrackingDataByStateWithInvalidStateShouldReturn400() throws Exception {
        this.mockMvc.perform(get("/api/v1/states/AA/current"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value(containsString("Invalid state code")));
    }
}