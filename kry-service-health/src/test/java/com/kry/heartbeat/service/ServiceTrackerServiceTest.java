package com.kry.heartbeat.service;

import com.kry.heartbeat.dao.ServiceTrackerRepository;
import com.kry.heartbeat.model.dto.ServiceTrackerDto;
import com.kry.heartbeat.model.entity.ServiceTracker;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.model.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTrackerServiceTest {

    @Test
    void getAllShouldReturnDtos() {
        Long userId = 2L;

        List<ServiceTracker> trackerEntities = new ArrayList<>();
        ServiceTracker serviceTracker = new ServiceTracker();
        serviceTracker.setCreatedAt(Instant.ofEpochMilli(32423324L));
        serviceTracker.setUser(new User());
        serviceTracker.setStatus(new Status());
        trackerEntities.add(serviceTracker);
        trackerEntities.add(serviceTracker);

        ServiceTrackerRepository serviceTrackerRepository = Mockito.mock(ServiceTrackerRepository.class);
        Mockito.when(serviceTrackerRepository.findByUser_Id(userId)).thenReturn(trackerEntities);

        ServiceTrackerService serviceTrackerService = new ServiceTrackerService(serviceTrackerRepository, null, null, null);


        List<ServiceTrackerDto> serviceTrackerDtos = serviceTrackerService.getAll(userId);


        assertThat(serviceTrackerDtos).isNotNull();
        assertThat(serviceTrackerDtos).hasSize(2);


    }

    @Test
    void findByIdShouldReturnDtoWhenExist() {


        ServiceTrackerRepository serviceTrackerRepository = Mockito.mock(ServiceTrackerRepository.class);

        ServiceTracker serviceTracker = new ServiceTracker();
        long id = 5L;
        serviceTracker.setId(id);
        serviceTracker.setCreatedAt(Instant.ofEpochMilli(32423324L));
        serviceTracker.setUser(new User());
        serviceTracker.setStatus(new Status());
        serviceTracker.setName("name");

        Optional<ServiceTracker> optionalServiceTracker = Optional.of(serviceTracker);

        Mockito.when(serviceTrackerRepository.findById(id)).thenReturn(optionalServiceTracker);

        ServiceTrackerService serviceTrackerService = new ServiceTrackerService(serviceTrackerRepository, null, null, null);


        ServiceTrackerDto serviceTrackerDto = serviceTrackerService.findById(id);


        assertThat(serviceTrackerDto).isNotNull();
        assertThat(serviceTrackerDto.getName()).isEqualTo(serviceTracker.getName());
    }

    @Test
    void findByIdShouldReturnNullWhenNotExist() {


        ServiceTrackerRepository serviceTrackerRepository = Mockito.mock(ServiceTrackerRepository.class);

        long id = 5L;

        Mockito.when(serviceTrackerRepository.findById(id)).thenReturn(Optional.empty());

        ServiceTrackerService serviceTrackerService = new ServiceTrackerService(serviceTrackerRepository, null, null, null);

        ServiceTrackerDto serviceTrackerDto = serviceTrackerService.findById(id);


        assertThat(serviceTrackerDto).isNull();
    }


}
