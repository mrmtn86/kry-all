package com.kry.heartbeat.controller;

import com.kry.heartbeat.dao.UserRepository;
import com.kry.heartbeat.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailShouldReturnExistingUser() throws Exception {

        User user = new User();
        user.setEmail("mtn@mtn.com");
        user.setName("mtn");

       user= userRepository.save(user);

        mockMvc.perform(get("/v1/user").param("email", user.getEmail()))
                .andDo(print())
                .andExpect(jsonPath("name", equalTo(user.getName())))
                .andExpect(jsonPath("email", equalTo(user.getEmail())));
    }

    @Test
    void findByEmailShouldReturnNullForNonExistingUser() throws Exception {
        mockMvc.perform(get("/v1/user").param("email", "non existing email"))
                .andDo(print()).andExpect(jsonPath("$").doesNotExist());
    }
}
