package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.repository.BandRepository;
import cz.muni.fi.pa165.modulecore.mapper.BandMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BandRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(BandRestControllerTest.class);
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BandRepository bandRepository;
    private final BandMapper bandMapper;

    @Autowired
    public BandRestControllerTest(MockMvc mockMvc,
                                  ObjectMapper objectMapper,
                                  BandRepository bandRepository,
                                  BandMapper bandMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bandRepository = bandRepository;
        this.bandMapper = bandMapper;
    }

    @Test
    void testBandFindByIdOK() throws Exception {
        log.debug("testBandFindByIdOK running");

        BandDto expectedResponse = bandMapper.mapToDto(bandRepository.findAll().get(0));

        String response =
                mockMvc.perform(get(String.format("/bands/%s", expectedResponse.getId())))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        log.debug("response: {}", response);
        BandDto bandResponse = objectMapper.readValue(response, BandDto.class);
        assertThat("response", bandResponse, is(equalTo(expectedResponse)));
    }

    @Test
    void testBandFindByIdNotFound() throws Exception {
        log.debug("testBandFindByIdNotFound running");
        mockMvc.perform(get("/bands/-1")).andExpect(status().isNotFound());
    }

    @Test
    void testBandFindAll() throws Exception {
        log.debug("testBandFindAll running");

        String response = mockMvc.perform(get("/bands"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.debug("response: {}", response);
        List<BandDto> bandsResponse =
                objectMapper.readerForListOf(BandDto.class).readValue(response);
        assertThat("response",
                bandsResponse,
                is(equalTo(bandRepository.findAll()
                        .stream()
                        .map(bandMapper::mapToDto).toList())));
    }

    @Test
    void testBandCreateInvalidObject() throws Exception {
        log.debug("testBandCreateInvalidObject running");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "INVALID");

        mockMvc.perform(post("/bands").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBandCreateOK() throws Exception {
        log.debug("testBandCreateOK running");

        BandDto expectedResponse = new BandDto(null, "BestBand", Genre.FUNK, new Byte[0], 100L);
        String response = mockMvc.perform(post("/bands").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                expectedResponse)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.debug("response: {}", response);
        BandDto bandResponse = objectMapper.readValue(response, BandDto.class);
        expectedResponse.setId(bandResponse.getId());
        assertThat("response", bandResponse, is(equalTo(expectedResponse)));
    }

    @Test
    void testBandUpdate() throws Exception {
        log.debug("testBandUpdate running");

        BandDto expectedResponse = bandMapper.mapToDto(bandRepository.findById(3L));
        String response = mockMvc.perform(put(String.format("/bands/%s",
                        expectedResponse.getId())).contentType(
                                MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(
                                        expectedResponse)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.debug("response: {}", response);
        BandDto bandResponse = objectMapper.readValue(response, BandDto.class);
        assertThat("response", bandResponse, is(equalTo(expectedResponse)));
    }

    @Test
    void testBandUpdateInvalidObject() throws Exception {
        log.debug("testBandUpdateInvalidObject running");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "INVALID");

        mockMvc.perform(put(String.format("/bands/%s",
                        4L)).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBandUpdateMissingId() throws Exception {
        log.debug("testBandUpdateMissingId running");
        BandDto expectedResponse = bandMapper.mapToDto(bandRepository.findById(3L));
        mockMvc.perform(put(String.format("/bands/%s",
                        0L)).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                expectedResponse)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testBandDeleteOK() throws Exception {
        log.debug("testBandDeleteOK running");

        BandDto expectedResponse = bandMapper.mapToDto(bandRepository.findAll().get(0));

        mockMvc.perform(delete(String.format("/bands/%s", expectedResponse.getId())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void testBandDeleteNotFound() throws Exception {
        log.debug("testBandDeleteNotFound running");

        mockMvc.perform(delete(String.format("/bands/%s", 0L)))
                .andExpect(status().isNotFound());
    }
}