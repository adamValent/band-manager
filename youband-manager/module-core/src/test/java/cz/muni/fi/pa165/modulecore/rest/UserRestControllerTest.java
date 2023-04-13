package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.UserDto;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.UserRepository;
import cz.muni.fi.pa165.modulecore.mapper.UserMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {
    private final MockMvc mockMvc;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserRestControllerTest(MockMvc mockMvc, UserRepository userRepository,
                                  UserMapper userMapper, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.objectMapper = objectMapper;
    }

    @Test
    void findByIdOk() throws Exception {
        UserDto expectedResponse = userMapper.mapToDto(userRepository.findById(10L));
        String response = mockMvc.perform(get("/users/10"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                is(equalTo(expectedResponse)));
    }

    @Test
    void findByIdNotFound() throws Exception {
        mockMvc.perform(get("/users/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll() throws Exception {
        String response = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readerForListOf(UserDto.class).readValue(response),
                is(equalTo(userMapper.mapToList(userRepository.getAll()))));
    }

    @Test
    void createUserOk() throws Exception {
        UserDto expectedResponse = userMapper.mapToDto(
                new User(null, UserType.BAND_MEMBER, "John", "Person", "john@person.com",
                        "password"));
        String response = mockMvc.perform(post("/users")
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
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserOk() throws Exception {
        UserDto expectedResponse = userMapper.mapToDto(userRepository.findById(20L));
        expectedResponse.setFirstName("John");
        expectedResponse.setLastName("Person");
        expectedResponse.setEmail("john@person.com");
        String response = mockMvc.perform(put("/users/20")
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
        mockMvc.perform(put("/users/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUserOk() throws Exception {
        mockMvc.perform(delete("/users/30"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserNotFound() throws Exception {
        mockMvc.perform(delete("/users/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void registerUser() throws Exception {
        UserDto user = userMapper.mapToDto(
                new User(20L, UserType.BAND_MEMBER, "John", "Person", "john@person.com",
                        "password"));
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void loginUser() throws Exception {
        UserDto user = userMapper.mapToDto(
                new User(20L, UserType.BAND_MEMBER, "John", "Person", "john@person.com",
                        "password"));
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void logoutUser() throws Exception {
        mockMvc.perform(post("/users/logout"))
                .andExpect(status().isOk());
    }
}