package com.altimetrik.playground.covidtracker;

import com.altimetrik.playground.covidtracker.dto.TrackingDataDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CovidTrackerApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetUsTrackingDataShouldReturn202() throws Exception {
        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/us/current",
                TrackingDataDTO.class).getStatusCode())
                .isEqualTo(HttpStatus.ACCEPTED);
    }

    @Test
    public void testGetStateTrackingDataShouldReturn202() throws Exception {
        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/states/TX/current",
                TrackingDataDTO.class).getStatusCode())
                .isEqualTo(HttpStatus.ACCEPTED);
    }

    @Test
    public void testGetStateTrackingDataWithLessThan2CharactersShouldReturn400() throws Exception {
        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/states/X/current",
                TrackingDataDTO.class).getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetStateTrackingDataWithMoreThan2CharactersShouldReturn400() throws Exception {
        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/states/TXX/current",
                TrackingDataDTO.class).getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetStateTrackingDataWithInvalidStateShouldReturn400() throws Exception {
        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/v1/states/TT/current",
                TrackingDataDTO.class).getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void contextLoads() {
    }

}
