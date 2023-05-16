package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.AlbumDto;
import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.repository.AlbumRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.mapper.AlbumMapper;
import org.assertj.core.util.Lists;
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
import java.util.Collections;
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
class AlbumRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(AlbumRestControllerTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @MockBean
    private AlbumRepository albumRepository;

    @Test
    void testAlbumsFindByIdOK() throws Exception {
        log.debug("testActivityFindByIdOK running");
        Album album = new Album(1L, "name", LocalDate.now(), Genre.BLUES, Collections.emptyList(), new Band());
        Mockito.when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        AlbumDto expectedResponse = albumMapper.mapToDto(album);

        String response = mockMvc.perform(get(String.format("/albums/%s", expectedResponse.getId()))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.debug("response: {}", response);
        AlbumDto responseTour = objectMapper.readValue(response, AlbumDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }


    @Test
    void testAlbumsFindByIdOKNotFound() throws Exception {
        log.debug("testAlbumsFindByIdOKNotFound running");
        Mockito.when(albumRepository.findById(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/albums/10000")
                .with(opaqueToken())).andExpect(status().isNotFound());
    }

    @Test
    void testAlbumsGetAll() throws Exception {
        log.debug("testAlbumsGetAll running");
        Album album1 = new Album(1L, "name", LocalDate.now(), Genre.BLUES, Collections.emptyList(), new Band());
        Album album2 = new Album(2L, "name2", LocalDate.now(), Genre.BLUES, Collections.emptyList(), new Band());
        Mockito.when(albumRepository.findAll()).thenReturn(List.of(album1, album2));

        String response = mockMvc.perform(get("/albums")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.debug("response: {}", response);
        List<AlbumDto> responseTours = objectMapper.readerForListOf(AlbumDto.class).readValue(response);
        assertThat("response", responseTours, is(equalTo(Lists.newArrayList(albumRepository.findAll()).stream().map(a -> albumMapper.mapToDto(a)).toList())));
    }

    @Test
    void testAlbumCreateInvalidAlbum() throws Exception {
        log.debug("testAlbumCreateInvalidAlbum running");

        JSONObject json = new JSONObject();
        json.put("name", "INVALID");

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumCreateInvalidSongFormat() throws Exception {
        log.debug("testAlbumCreateInvalidSongFormat running");

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"releaseDate\":\"1993-09-21\",\"bandId\":0,\"songs\":[{\"title\":\"INVALID\"}],\"name\":\"In Utero\",\"genre\":\"ROCK\",\"id\":100}")
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumCreateInvalidSong() throws Exception {
        log.debug("testAlbumCreateInvalidSong running");

        String invalidSongs = "{\"id\":1000,\"name\":\"In Utero\",\"releaseDate\":\"1990-03-19\",\"genre\":\"ROCK\",\"songs\":[{\"id\":null,\"title\":null,\"duration\":null}]}";

        mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidSongs)
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumCreateOK() throws Exception {
        log.debug("testActivityCreateOK running");
        Album album = new Album(null, "name", LocalDate.now(), Genre.BLUES, Collections.emptyList(), new Band());
        AlbumDto expectedResponse = albumMapper.mapToDto(album);
        Mockito.when(albumRepository.save(album)).thenReturn(album);

        String response = mockMvc.perform(post("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.debug("response: {}", response);
        AlbumDto responseTour = objectMapper.readValue(response, AlbumDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testAlbumUpdate() throws Exception {
        log.debug("testAlbumUpdate running");
        Album album = new Album(1L, "name", LocalDate.now(), Genre.BLUES, Collections.emptyList(), new Band());
        AlbumDto expectedResponse = albumMapper.mapToDto(album);
        Mockito.when(albumRepository.save(album)).thenReturn(album);
        Mockito.when(albumRepository.existsById(album.getId())).thenReturn(true);

        String response = mockMvc.perform(put(String.format("/albums/%s", expectedResponse.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.debug("response: {}", response);
        AlbumDto responseTour = objectMapper.readValue(response, AlbumDto.class);
        assertThat("response", responseTour, is(equalTo(expectedResponse)));
    }

    @Test
    void testAlbumUpdateInvalidObject() throws Exception {
        log.debug("testAlbumUpdateInvalidObject running");
        String invalidSongs = "{\"id\":100,\"name\":\"In Utero\",\"releaseDate\":\"1990-03-19\",\"genre\":\"ROCK\",\"songs\":[{\"id\":null,\"title\":null,\"duration\":null}]}";

        mockMvc.perform(put("/albums/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidSongs)
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAlbumDeleteOK() throws Exception {
        log.debug("testAlbumDeleteOK running");
        Mockito.doNothing().when(albumRepository).deleteById(1L);

        mockMvc.perform(delete(String.format("/albums/%s", 1L))
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void testAlbumDeleteNotFound() throws Exception {
        log.debug("testAlbumDeleteNotFound running");
        Mockito.doThrow(new ResourceNotFoundException()).when(albumRepository).deleteById(0L);

        mockMvc.perform(delete(String.format("/albums/%s", 0L))
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAllByBandIdMix() throws Exception {
        Album album1 = new Album(null, "name", LocalDate.now(), Genre.ROCK, Collections.emptyList(), new Band());
        Album album2 = new Album(null, "name2", LocalDate.now(), Genre.ROCK, Collections.emptyList(), new Band());
        Mockito.when(albumRepository.findAllByBandId(1L)).thenReturn(List.of(album1, album2));

        String response = mockMvc.perform(get("/albums/allByBand/1")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<AlbumDto> foundAlbums = objectMapper.readerForListOf(AlbumDto.class).readValue(response);
        assertThat(foundAlbums.size(), is(equalTo(2)));
    }
}