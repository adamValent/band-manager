package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.SongDto;
import cz.muni.fi.pa165.modulecore.api.UserDto;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.UserRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.mapper.UserMapper;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
        User user = new User(1L, UserType.MANAGER, "name", "last", "mail", "pwd");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        String response = mockMvc.perform(get("/users/1"))
                                 .andExpect(status().isOk())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                   is(equalTo(userMapper.mapToDto(userRepository.findById(1L).get()))));
    }

    @Test
    void findByIdNotFound() throws Exception {
        Mockito.when(userRepository.findById(0L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/users/0")).andExpect(status().isNotFound());
    }

    @Test
    void getAll() throws Exception {
        String response = mockMvc.perform(get("/users"))
                                 .andExpect(status().isOk())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString();
        assertThat(objectMapper.readerForListOf(UserDto.class).readValue(response),
                   is(equalTo(userMapper.mapToList(Lists.newArrayList(userRepository.findAll())))));
    }

    @Test
    void createUserOk() throws Exception {
        User user = new User(null, UserType.MANAGER, "name", "last", "mail", "pwd");
        UserDto expectedResponse = userMapper.mapToDto(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        String response = mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(
                                                                expectedResponse)))
                                 .andExpect(status().isOk())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString();

        UserDto actual = objectMapper.readValue(response, UserDto.class);
        expectedResponse.setId(actual.getId());
        assertThat(actual, is(equalTo(expectedResponse)));
    }

    @Test
    void createUserBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.toString()))
               .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserOk() throws Exception {
        User user = new User(1L, UserType.MANAGER, "name", "last", "mail", "pwd");
        UserDto expectedResponse = userMapper.mapToDto(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.existsById(user.getId())).thenReturn(true);
        String response = mockMvc.perform(put("/users/1")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(objectMapper.writeValueAsString(expectedResponse)))
                                 .andExpect(status().isOk())
                                 .andReturn()
                                 .getResponse()
                                 .getContentAsString();
        UserDto actual = objectMapper.readValue(response, UserDto.class);
        assertThat(actual, is(equalTo(expectedResponse)));
    }

    @Test
    void updateUserBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(put("/users/100")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content.toString()))
               .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUserOk() throws Exception {
        Mockito.doNothing().when(userRepository).deleteById(1L);
        mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
    }

    @Test
    void deleteUserNotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(userRepository).deleteById(0L);
        mockMvc.perform(delete("/users/0")).andExpect(status().isNotFound());
    }

    @Test
    void registerUser() throws Exception {
        UserDto user = userMapper.mapToDto(new User(20L,
                                                    UserType.BAND_MEMBER,
                                                    "John",
                                                    "Person",
                                                    "john@person.com",
                                                    "password"));
        mockMvc.perform(post("/users/registration").contentType(MediaType.APPLICATION_JSON)
                                                   .content(objectMapper.writeValueAsString(user)))
               .andExpect(status().isOk());
    }

    @Test
    void loginUser() throws Exception {
        UserDto user = userMapper.mapToDto(new User(20L,
                                                    UserType.BAND_MEMBER,
                                                    "John",
                                                    "Person",
                                                    "john@person.com",
                                                    "password"));
        mockMvc.perform(post("/users/login").contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(user)))
               .andExpect(status().isOk());
    }

    @Test
    void logoutUser() throws Exception {
        mockMvc.perform(post("/users/logout")).andExpect(status().isOk());
    }
}