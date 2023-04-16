package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.api.UserDto;
import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.BandRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.mapper.BandMapper;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    private final BandMapper bandMapper;

    @MockBean
    private BandRepository bandRepository;

    private Band testingBand;

    @Autowired
    public BandRestControllerTest(MockMvc mockMvc,
                                  ObjectMapper objectMapper,
                                  BandMapper bandMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bandMapper = bandMapper;

        User user = new User(1L, UserType.MANAGER, "test", "test", "test");
        testingBand = new Band(1L, "TEST", Genre.ROCK, new Byte[]{}, user);

    }

    @Test
    void testBandFindByIdOK() throws Exception {
        log.debug("testBandFindByIdOK running");

        Mockito.when(bandRepository.findById(testingBand.getId())).thenReturn(Optional.of(testingBand));
        BandDto expectedResponse = bandMapper.mapToDto(testingBand);

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

        Mockito.when(bandRepository.findAll()).thenReturn(List.of(testingBand));

        String response = mockMvc.perform(get("/bands"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.debug("response: {}", response);
        List<BandDto> bandsResponse = objectMapper.readerForListOf(BandDto.class).readValue(response);
        assertThat("response",
                bandsResponse,
                is(equalTo(Stream.of(testingBand)
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

        Mockito.when(bandRepository.save(testingBand)).thenReturn(testingBand);

        BandDto expectedResponse = bandMapper.mapToDto(testingBand);
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

        Mockito.when(bandRepository.save(testingBand)).thenReturn(testingBand);
        Mockito.when(bandRepository.existsById(testingBand.getId())).thenReturn(true);

        BandDto expectedResponse = bandMapper.mapToDto(testingBand);

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
    void testBandDeleteOK() throws Exception {
        log.debug("testBandDeleteOK running");

        Mockito.doNothing().when(bandRepository).deleteById(testingBand.getId());

        mockMvc.perform(delete(String.format("/bands/%s", testingBand.getId())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void testBandDeleteNotFound() throws Exception {
        log.debug("testBandDeleteNotFound running");

        Mockito.doThrow(new ResourceNotFoundException()).when(bandRepository).deleteById(0L);

        mockMvc.perform(delete(String.format("/bands/%s", 0L)))
                .andExpect(status().isNotFound());
    }
}