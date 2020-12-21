package com.kry.heartbeat.dao;

import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;

    @Test
    void findByKeyReturnsExistingStatus() {

        Status status = new Status();
        status.setName("OK");
        status.setKey(StatusKey.OK);

        statusRepository.save(status);


        Status okStatus = statusRepository.findByKey(StatusKey.OK);

        assertThat(okStatus).isNotNull();
        assertThat(okStatus.getKey()).isEqualTo(StatusKey.OK);
    }

    @Test
    void findByKeyReturnsNullForNonExistingStatus() {
        Status okStatus = statusRepository.findByKey(StatusKey.OK);

        assertThat(okStatus).isNull();
    }
}
