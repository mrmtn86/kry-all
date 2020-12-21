package com.kry.heartbeat.dao;


import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.ServiceTracker;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ServiceTrackerRepositoryTest {

    @Autowired
    private ServiceTrackerRepository serviceTrackerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Test
    void findByUser_IdShouldReturnExistingServiceTracker() {


        ServiceTracker serviceTracker = new ServiceTracker();
        serviceTracker.setName("name");
        String url = "http://valid.url";
        serviceTracker.setUrl(url);

        Status status = new Status();
        status.setName("OK");
        status.setKey(StatusKey.OK);

        statusRepository.save(status);

        serviceTracker.setStatus(status);


        User user = new User();
        user.setEmail("email");
        user.setName("name");
        userRepository.save(user);

        serviceTracker.setUser(user);

        serviceTrackerRepository.save(serviceTracker);


        List<ServiceTracker> trackers = serviceTrackerRepository.findByUser_Id(user.getId());

        assertThat(trackers).isNotNull();
        assertThat(trackers).hasSize(1);
        assertThat(trackers.get(0).getUrl()).isEqualTo(url);
    }
}
