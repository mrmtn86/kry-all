package com.kry.heartbeat.model.converter;

import ch.qos.logback.core.util.TimeUtil;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.dto.ServiceTrackerDto;
import com.kry.heartbeat.model.entity.ServiceTracker;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTrackerConverterTest {

    @Test
    void toDtoShouldReturnConvertedToDto() {
        ServiceTracker serviceTracker = new ServiceTracker();

        User user = new User();
        user.setEmail("email");
        user.setName("name");
        user.setId(5L);
        serviceTracker.setUser(user);

        serviceTracker.setUrl("url");
        serviceTracker.setName("s-name");


        Status status = new Status();
        status.setKey(StatusKey.OK);
        status.setName("Ok");
        serviceTracker.setStatus(status);


        ServiceTrackerDto serviceTrackerDto = ServiceTrackerConverter.toDto(serviceTracker);


        assertThat(serviceTrackerDto).isNotNull();
        assertThat(serviceTrackerDto.getName()).isEqualTo(serviceTracker.getName());
        assertThat(serviceTrackerDto.getStatus()).isEqualTo(serviceTracker.getStatus().getName());
        assertThat(serviceTrackerDto.getUrl()).isEqualTo(serviceTracker.getUrl());
        assertThat(serviceTrackerDto.getUserId()).isEqualTo(serviceTracker.getUser().getId());

    }

    @Test
    void toDtoShouldReturnNullWhenServiceTrackerISNull() {
        assertThat(ServiceTrackerConverter.toDto((ServiceTracker) null)).isNull();
    }

    @Test
    void toDtoShouldReturnConvertedToDtoListInOrderCreatedDate() {
        ServiceTracker serviceTracker = new ServiceTracker();

        User user = new User();
        user.setEmail("email");
        user.setName("name");
        user.setId(5L);
        serviceTracker.setUser(user);

        serviceTracker.setUrl("url");
        String t1 = "t1";
        serviceTracker.setName(t1);
        Instant c1 = Instant.ofEpochMilli(42345689L);
        serviceTracker.setCreatedAt(c1);


        Status status = new Status();
        status.setKey(StatusKey.OK);
        status.setName("Ok");
        serviceTracker.setStatus(status);


        //add 1


        ServiceTracker serviceTracker2 = new ServiceTracker();

        serviceTracker2.setUser(user);

        serviceTracker2.setUrl("url");
        serviceTracker2.setName(t1);
        serviceTracker2.setCreatedAt(Instant.ofEpochMilli(12345689L));

        serviceTracker2.setStatus(status);

        String t2 = "t2";
        serviceTracker.setName(t2);

        List<ServiceTracker> list = new ArrayList<>();

        list.add(serviceTracker);
        list.add(serviceTracker2);

        List<ServiceTrackerDto> serviceTrackerDtoList = ServiceTrackerConverter.toDto(list);

        assertThat(serviceTrackerDtoList).isNotNull();
        assertThat(serviceTrackerDtoList).hasSize(2);

        assertThat(serviceTrackerDtoList.get(0).getName()).isEqualTo(t1);
        assertThat(serviceTrackerDtoList.get(1).getName()).isEqualTo(t2);
    }

    @Test
    void toDtoShouldReturnNullWhenNullListPassed() {
        assertThat(ServiceTrackerConverter.toDto((Iterable<ServiceTracker>) null)).isNull();
    }
}
