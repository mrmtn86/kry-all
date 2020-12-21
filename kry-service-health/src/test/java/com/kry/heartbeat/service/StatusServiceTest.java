package com.kry.heartbeat.service;


import com.kry.heartbeat.dao.StatusRepository;
import com.kry.heartbeat.exception.BusinessRuleException;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.Status;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class StatusServiceTest {

    @Test
    void findByKeyShouldReturnStatus() throws BusinessRuleException {
        StatusKey key = StatusKey.OK;
        Status status = new Status("Ok", key);

        StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
        Mockito.when(statusRepository.findByKey(key)).thenReturn(status);


        StatusService statusService = new StatusService(statusRepository);


        Status statusResult = statusService.findByKey(key);


        assertThat(statusResult).isNotNull();
        assertThat(statusResult.getKey()).isEqualTo(key);

    }

    @Test
    void findByKeyShouldThrowBusinesExceptionForUnknownKey() {
        StatusKey key = StatusKey.OK;

        StatusRepository statusRepository = Mockito.mock(StatusRepository.class);
        Mockito.when(statusRepository.findByKey(key)).thenReturn(null);


        StatusService statusService = new StatusService(statusRepository);

        Assert.assertThrows(BusinessRuleException.class, () -> statusService.findByKey(key));



    }
}
