package com.kry.heartbeat.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.TestUtil.asJsonString;


import com.kry.heartbeat.dao.ServiceTrackerRepository;
import com.kry.heartbeat.dao.StatusRepository;
import com.kry.heartbeat.dao.UserRepository;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.converter.ServiceTrackerConverter;
import com.kry.heartbeat.model.dto.ServiceTrackerDto;
import com.kry.heartbeat.model.entity.ServiceTracker;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class ServiceTrackerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ServiceTrackerController serviceTrackerController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ServiceTrackerRepository serviceTrackerRepository;
    ServiceTracker serviceTracker;
    User user;
    Status status;

    @BeforeEach
    public void init() {

        status = new Status();
        status.setName("FAIL");
        status.setKey(StatusKey.FAIL);
        statusRepository.save(status);

        user = new User();
        user.setName("mtn");
        user.setEmail("mtn@mtn.com");

        userRepository.save(user);


        serviceTracker = new ServiceTracker();
        serviceTracker.setUrl("http://localhost:9878");
        serviceTracker.setStatus(status);
        serviceTracker.setName("service name");
        serviceTracker.setUser(user);
        serviceTracker.setStatusLastChangeTime(Instant.ofEpochMilli(3215343543L));

        serviceTrackerRepository.save(serviceTracker);

    }

    @Test
    public void contextLoads() {
        assertThat(serviceTrackerController).isNotNull();
    }

    @Test
    void getServiceTrackersShouldReturnTrackers() throws Exception {

        mockMvc.perform(get("/v1/service-tracker").param("userId", user.getId().toString()))
                .andDo(print())
                .andExpect(jsonPath("$[0].name", equalTo(serviceTracker.getName())))
                .andExpect(jsonPath("$[0].url", equalTo(serviceTracker.getUrl())))
                .andExpect(jsonPath("$[0].status", equalTo(status.getName())));

    }

    @Test
    void findByIdShouldReturnTrackerWhenExist() throws Exception {

        mockMvc.perform(get("/v1/service-tracker").param("trackerId", serviceTracker.getId().toString()))
                .andDo(print())
                .andExpect(jsonPath("name", equalTo(serviceTracker.getName())))
                .andExpect(jsonPath("url", equalTo(serviceTracker.getUrl())))
                .andExpect(jsonPath("status", equalTo(status.getName())));
    }


    @Test
    void addServiceTrackerShouldAddNewTrackerToDb() throws Exception {
        ServiceTrackerDto newServiceTrackerDto = ServiceTrackerConverter.toDto(serviceTracker);

        serviceTrackerRepository.deleteAll();
        newServiceTrackerDto.setId(null);
        String random_test_uRl = "http://url.test";
        newServiceTrackerDto.setUrl(random_test_uRl);

        mockMvc.perform(post("/v1/service-tracker")
                .content(asJsonString(newServiceTrackerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        ServiceTracker serviceTrackerResult = serviceTrackerRepository.findAll().get(0);

        assertThat(serviceTrackerResult.getUrl()).isEqualTo(random_test_uRl);

    }

    @Test
    void addServiceTrackerShouldUpdateCurrentTracker() throws Exception {

        String random_test_uRl = "http://random.url";
        serviceTracker.setUrl(random_test_uRl);

        ServiceTrackerDto newServiceTrackerDto = ServiceTrackerConverter.toDto(serviceTracker);

        mockMvc.perform(post("/v1/service-tracker")
                .content(asJsonString(newServiceTrackerDto))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated());


        List<ServiceTracker> all = serviceTrackerRepository.findAll();
        assertThat(all).hasSize(1);
        ServiceTracker serviceTrackerResult = all.get(0);

        assertThat(serviceTrackerResult.getUrl()).isEqualTo(random_test_uRl);

    }


    @Test
    void deleteShouldDeleteTracker() throws Exception {
        mockMvc.perform(delete("/v1/service-tracker").param("trackerId", serviceTracker.getId().toString()))
                .andDo(print());

        Optional<ServiceTracker> tracker = serviceTrackerRepository.findById(serviceTracker.getId());
        assertFalse(tracker.isPresent());
    }
}
