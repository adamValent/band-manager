package cz.muni.fi.pa165.modulealbums;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulealbums.api.AlbumDto;
import cz.muni.fi.pa165.modulealbums.api.SongDto;
import cz.muni.fi.pa165.modulealbums.data.enums.Genre;
import cz.muni.fi.pa165.modulealbums.data.repository.AlbumRepository;
import cz.muni.fi.pa165.modulealbums.mappers.AlbumMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class ModuleAlbumsApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(ModuleAlbumsApplicationTests.class);


    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AlbumRepository albumRepository;
    private AlbumMapper albumMapper;

    @Autowired
    public ModuleAlbumsApplicationTests(ObjectMapper objectMapper,
                                        MockMvc mockMvc,
                                        AlbumRepository albumRepository,
                                        AlbumMapper albumMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testAlbumsFindByIdOK() throws Exception {
        log.debug("testActivityFindByIdOK running");

        AlbumDto expectedResponse = albumMapper.mapToDto(albumRepository.getAll().get(0));

        String response = mockMvc.perform(get(String.format("/api/albums/%s", expectedResponse.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        AlbumDto responseTour = objectMapper.readValue(response, AlbumDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }


    @Test
    void testAlbumsFindByIdOKNotFound() throws Exception {
        log.debug("testAlbumsFindByIdOKNotFound running");

        mockMvc.perform(get("/api/albums/10000")).andExpect(status().isNotFound());
    }

    @Test
    void testAlbumsGetAll() throws Exception {
        log.debug("testAlbumsGetAll running");

        String response = mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        List<AlbumDto> responseTours = objectMapper.readerForListOf(AlbumDto.class).readValue(response);
        assertThat("response", responseTours, is(equalTo(albumRepository.getAll().stream().map(tour -> albumMapper.mapToDto(tour)).toList())));
    }

    @Test
    void testAlbumCreateInvalidAlbum() throws Exception {
        log.debug("testAlbumCreateInvalidAlbum running");

        JSONObject json = new JSONObject();
        json.put("name", "INVALID");

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumCreateInvalidSongFormat() throws Exception {
        log.debug("testAlbumCreateInvalidSongFormat running");

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"releaseDate\":\"1993-09-21\",\"bandId\":0,\"songs\":[{\"title\":\"INVALID\"}],\"name\":\"In Utero\",\"genre\":\"ROCK\",\"id\":100}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumCreateInvalidSong() throws Exception {
        log.debug("testAlbumCreateInvalidSong running");

        String invalidSongs = "{\"id\":1000,\"name\":\"In Utero\",\"releaseDate\":\"1990-03-19\",\"genre\":\"ROCK\",\"songs\":[{\"id\":null,\"title\":null,\"duration\":null}]}";

        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidSongs))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumCreateOK() throws Exception {
        log.debug("testActivityCreateOK running");

        AlbumDto expectedResponse =
                new AlbumDto(1500L,
                        "Violator",
                        LocalDate.of(1990, 3, 19),
                        Genre.ROCK,
                        List.of(new SongDto(1L, "Personal Jesus", Duration.ofSeconds(295)),
                                new SongDto(2L, "Sweetest perfection", Duration.ofSeconds(282)),
                                new SongDto(3L, "Waiting for the night", Duration.ofSeconds(367))), 0L);


        String response = mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        AlbumDto responseTour = objectMapper.readValue(response, AlbumDto.class);
        expectedResponse.setId(responseTour.getId());
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testAlbumUpdate() throws Exception {
        log.debug("testAlbumUpdate running");

        AlbumDto expectedResponse = albumMapper.mapToDto(albumRepository.findById(100L));
        expectedResponse.setSongs(new ArrayList<>());

        String response = mockMvc.perform(put(String.format("/api/albums/%s", expectedResponse.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
        AlbumDto responseTour = objectMapper.readValue(response, AlbumDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testAlbumUpdateMissingId() throws Exception {
        log.debug("testAlbumUpdateMissingId running");

        AlbumDto expectedResponse = albumMapper.mapToDto(albumRepository.findById(100L));
        expectedResponse.setSongs(new ArrayList<>());

        mockMvc.perform(put(String.format("/api/albums/%s", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAlbumUpdateInvalidObject() throws Exception {
        log.debug("testAlbumUpdateInvalidObject running");

        String invalidSongs = "{\"id\":100,\"name\":\"In Utero\",\"releaseDate\":\"1990-03-19\",\"genre\":\"ROCK\",\"songs\":[{\"id\":null,\"title\":null,\"duration\":null}]}";

        mockMvc.perform(put("/api/albums/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidSongs))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumDeleteOK() throws Exception {
        log.debug("testAlbumDeleteOK running");

        AlbumDto expectedResponse = albumMapper.mapToDto(albumRepository.getAll().get(0));

        mockMvc.perform(delete(String.format("/api/albums/%s", expectedResponse.getId())))
                .andExpect(status().isOk());
    }

    @Test
    void testAlbumDeleteNotFound() throws Exception {
        log.debug("testAlbumDeleteNotFound running");

        mockMvc.perform(delete(String.format("/api/albums/%s", 0L)))
                .andExpect(status().isNotFound());
    }










}
