package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.SongDto;
import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.data.repository.SongRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.mapper.CycleAvoidingMappingContext;
import cz.muni.fi.pa165.modulecore.mapper.SongMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SongRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SongMapper songMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SongRepository songRepository;

    @Test
    void findByIdOk() throws Exception {
        Song song = new Song(1L, "title", Duration.ofSeconds(15), new Album());
        Mockito.when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        String response = mockMvc.perform(get("/songs/1")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readValue(response, SongDto.class),
                is(equalTo(songMapper.mapToDto(songRepository.findById(1L).get(), new CycleAvoidingMappingContext()))));
    }

    @Test
    void findByIdNotFound() throws Exception {
        Mockito.when(songRepository.findById(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/songs/0")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createOk() throws Exception {
        Song song = new Song(null, "title", Duration.ofSeconds(15), new Album());
        SongDto expectedResponse = songMapper.mapToDto(song, new CycleAvoidingMappingContext());
        Mockito.when(songRepository.save(song)).thenReturn(song);

        String response = mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        SongDto actual = objectMapper.readValue(response, SongDto.class);
        expectedResponse.setId(actual.getId());
        assertThat(actual, is(equalTo(expectedResponse)));
    }

    @Test
    void createBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");

        mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateOk() throws Exception {
        Song song = new Song(2L, "title", Duration.ofSeconds(15), new Album());
        SongDto expectedResponse = songMapper.mapToDto(song, new CycleAvoidingMappingContext());
        Mockito.when(songRepository.save(song)).thenReturn(song);
        Mockito.when(songRepository.existsById(song.getId())).thenReturn(true);

        String response = mockMvc.perform(put("/songs/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse))
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readValue(response, SongDto.class),
                is(equalTo(expectedResponse)));
    }

    @Test
    void updateBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");

        mockMvc.perform(put("/songs/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteInvitationOk() throws Exception {
        Mockito.doNothing().when(songRepository).deleteById(1L);

        mockMvc.perform(delete("/songs/1")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(songRepository).deleteById(0L);

        mockMvc.perform(delete("/songs/0")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }
}