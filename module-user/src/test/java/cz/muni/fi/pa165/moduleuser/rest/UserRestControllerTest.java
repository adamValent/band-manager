package cz.muni.fi.pa165.moduleuser.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.moduleuser.api.UserDto;
import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.data.repository.UserRepository;
import cz.muni.fi.pa165.moduleuser.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.moduleuser.mapper.UserMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository userRepository;

    @Test
    void findByIdOk() throws Exception {
        User user = new User(1L, "me@mail.com", "psw");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        String response = mockMvc.perform(get("/users-auth/1"))
                                 .andExpect(status().isOk())
                                 .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                   is(equalTo(userMapper.mapToDto(userRepository.findById(1L).get()))));
    }

    @Test
    void findByIdNotFound() throws Exception {
        Mockito.when(userRepository.findById(0L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/users-auth/0"))
               .andExpect(status().isNotFound());
    }

    @Test
    void createUserOk() throws Exception {
        User user = new User(1L, "me@mail.com", "psw");
        UserDto expectedResponse = userMapper.mapToDto(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        String response = mockMvc.perform(post("/users-auth")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(expectedResponse)))
                                 .andExpect(status().isOk())
                                 .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                   is(equalTo(expectedResponse)));
    }

    @Test
    void createUserBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(post("/users-auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.toString()))
               .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserOk() throws Exception {
        User user = new User(1L, "me@mail.com", "psw");
        UserDto expectedResponse = userMapper.mapToDto(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.existsById(user.getId())).thenReturn(true);
        String response = mockMvc.perform(put("/users-auth/1")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(expectedResponse)))
                                 .andExpect(status().isOk())
                                 .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                   is(equalTo(expectedResponse)));
    }

    @Test
    void updateUserBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(put("/users-auth/20")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.toString()))
               .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUserOk() throws Exception {
        Mockito.doNothing().when(userRepository).deleteById(1L);
        mockMvc.perform(delete("/users-auth/1"))
               .andExpect(status().isOk());
    }

    @Test
    void deleteUserNotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(userRepository).deleteById(0L);
        mockMvc.perform(delete("/users-auth/0"))
               .andExpect(status().isNotFound());
    }
}