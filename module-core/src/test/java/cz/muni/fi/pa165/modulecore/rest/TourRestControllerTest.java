package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.librarymodel.api.TourDto;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Tour;
import cz.muni.fi.pa165.modulecore.data.model.TourDate;
import cz.muni.fi.pa165.modulecore.data.repository.TourRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.mapper.TourMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TourRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(TourRestControllerTest.class);

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final TourMapper tourMapper;

    @MockBean
    private TourRepository tourRepository;

    private final Tour testingTour;

    @Autowired
    public TourRestControllerTest(ObjectMapper objectMapper,
                                  MockMvc mockMvc,
                                  TourMapper tourMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.tourMapper = tourMapper;

        this.testingTour = new Tour(100L, "PopTour",
                new ArrayList<>(List.of(new Band())),
                new ArrayList<>(List.of(
                        new TourDate(0L, "New York", LocalDate.of(2023, 5, 5), "Venue1"),
                        new TourDate(1L, "Sydney", LocalDate.of(2023, 6, 3), "Venue2")
                )));

    }

    @Test
    void testActivityFindByIdOK() throws Exception {
        log.debug("testActivityFindByIdOK running");

        Mockito.when(tourRepository.findById(testingTour.getId())).thenReturn(Optional.of(testingTour));
        TourDto expectedResponse = tourMapper.mapToDto(testingTour);

        String response = mockMvc.perform(get(String.format("/tours/%s", expectedResponse.getId()))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        TourDto responseTour = objectMapper.readValue(response, TourDto.class);
        assertThat("response", responseTour.getName(), is(equalTo(expectedResponse.getName())));
    }

    @Test
    void testActivityFindByIdNotFound() throws Exception {
        log.debug("testActivityFindByIdNotFound running");

        Mockito.when(tourRepository.findById(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/tours/0")
                .with(opaqueToken())).andExpect(status().isNotFound());
    }


    @Test
    void testActivityGetAll() throws Exception {
        log.debug("testActivityGetAll running");

        Mockito.when(tourRepository.findAll()).thenReturn(List.of(testingTour));

        String response = mockMvc.perform(get("/tours")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        List<TourDto> responseTours = objectMapper.readerForListOf(TourDto.class).readValue(response);
        assertThat("response", responseTours, is(equalTo(((List<Tour>) tourRepository.findAll()).stream().map(tourMapper::mapToDto).toList())));
    }

    @Test
    void testActivityCreateInvalidObject() throws Exception {
        log.debug("testActivityCreateInvalidObject running");

        JSONObject json = new JSONObject();
        json.put("name", "INVALID");

        mockMvc.perform(post("/tours")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActivityCreateOK() throws Exception {
        log.debug("testActivityCreateOK running");

        Mockito.when(tourRepository.save(testingTour)).thenReturn(testingTour);
        TourDto expectedResponse = tourMapper.mapToDto(testingTour);

        String response = mockMvc.perform(post("/tours")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        TourDto responseTour = objectMapper.readValue(response, TourDto.class);
        expectedResponse.setId(responseTour.getId());
        assertThat("response", responseTour.getName(), is(equalTo(expectedResponse.getName())));
    }

    @Test
    void testActivityUpdate() throws Exception {
        log.debug("testActivityUpdate running");

        Mockito.when(tourRepository.save(testingTour)).thenReturn(testingTour);
        Mockito.when(tourRepository.existsById(testingTour.getId())).thenReturn(true);

        TourDto expectedResponse = tourMapper.mapToDto(testingTour);

        String response = mockMvc.perform(put(String.format("/tours/%s", expectedResponse.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse))
                        .with(opaqueToken()))
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

        mockMvc.perform(put("/tours/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActivityDeleteOK() throws Exception {
        log.debug("testActivityDeleteOK running");


        Mockito.doNothing().when(tourRepository).deleteById(testingTour.getId());
        TourDto expectedResponse = tourMapper.mapToDto(testingTour);

        mockMvc.perform(delete(String.format("/tours/%s", expectedResponse.getId()))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void testActivityDeleteNotFound() throws Exception {
        log.debug("testActivityDeleteNotFound running");

        Mockito.doThrow(new ResourceNotFoundException()).when(tourRepository).deleteById(0L);

        mockMvc.perform(delete(String.format("/tours/%s", 0L))
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

}