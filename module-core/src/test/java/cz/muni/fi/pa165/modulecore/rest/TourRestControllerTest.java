package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.TourDateDto;
import cz.muni.fi.pa165.modulecore.api.TourDto;
import cz.muni.fi.pa165.modulecore.data.repository.TourRepository;
import cz.muni.fi.pa165.modulecore.mapper.TourMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TourRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(TourRestControllerTest.class);

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    @Autowired
    public TourRestControllerTest(ObjectMapper objectMapper,
                                  MockMvc mockMvc,
                                  TourRepository tourRepository,
                                  TourMapper tourMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    @Test
    void testActivityFindByIdOK() throws Exception {
        log.debug("testActivityFindByIdOK running");

        TourDto expectedResponse = tourMapper.mapToDto(tourRepository.getAll().get(0));

        String response = mockMvc.perform(get(String.format("/tours/%s", expectedResponse.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        TourDto responseTour = objectMapper.readValue(response, TourDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testActivityFindByIdNotFound() throws Exception {
        log.debug("testActivityFindByIdNotFound running");

        mockMvc.perform(get("/tours/10000")).andExpect(status().isNotFound());
    }


    @Test
    void testActivityGetAll() throws Exception {
        log.debug("testActivityGetAll running");

        String response = mockMvc.perform(get("/tours"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        List<TourDto> responseTours = objectMapper.readerForListOf(TourDto.class).readValue(response);
        assertThat("response", responseTours, is(equalTo(tourRepository.getAll().stream().map(tour -> tourMapper.mapToDto(tour)).toList())));
    }

    @Test
    void testActivityCreateInvalidObject() throws Exception {
        log.debug("testActivityCreateInvalidObject running");

        JSONObject json = new JSONObject();
        json.put("name", "INVALID");

        mockMvc.perform(post("/tours")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActivityCreateOK() throws Exception {
        log.debug("testActivityCreateOK running");

        TourDto expectedResponse =
                new TourDto(null, "PopTour2",
                        List.of(5L, 6L),
                        List.of(
                                new TourDateDto("Dublin", LocalDate.of(2023, 4, 5), "Venue1"),
                                new TourDateDto("Belfast", LocalDate.of(2023, 4, 6), "Venue2")
                        ));

        String response = mockMvc.perform(post("/tours")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        TourDto responseTour = objectMapper.readValue(response, TourDto.class);
        expectedResponse.setId(responseTour.getId());
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testActivityUpdate() throws Exception {
        log.debug("testActivityUpdate running");

        TourDto expectedResponse = tourMapper.mapToDto(tourRepository.findById(41L));
        expectedResponse.setBandList(new ArrayList<>());

        String response = mockMvc.perform(put(String.format("/tours/%s", expectedResponse.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        TourDto responseTour = objectMapper.readValue(response, TourDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testActivityUpdateInvalidObject() throws Exception {
        log.debug("testActivityUpdateInvalidObject running");

        JSONObject json = new JSONObject();
        json.put("name", "INVALID");

        mockMvc.perform(put(String.format("/tours/%s", tourRepository.getAll().get(0).getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActivityUpdateMissingId() throws Exception {
        log.debug("testActivityUpdate running");

        TourDto expectedResponse = tourMapper.mapToDto(tourRepository.findById(41L));
        expectedResponse.setBandList(new ArrayList<>());

        mockMvc.perform(put(String.format("/tours/%s", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testActivityDeleteOK() throws Exception {
        log.debug("testActivityDeleteOK running");

        TourDto expectedResponse = tourMapper.mapToDto(tourRepository.getAll().get(0));

        mockMvc.perform(delete(String.format("/tours/%s", expectedResponse.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void testActivityDeleteNotFound() throws Exception {
        log.debug("testActivityDeleteNotFound running");

        mockMvc.perform(delete(String.format("/tours/%s", 0L)))
                .andExpect(status().isNotFound());
    }
}