package com.kry.heartbeat.model.converter;

import com.kry.heartbeat.model.dto.ServiceTrackerDto;
import com.kry.heartbeat.model.entity.ServiceTracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceTrackerConverter {


    public static List<ServiceTrackerDto> toDto(Iterable<ServiceTracker> trackers) {

        if (trackers == null) {
            return null;
        }
        List<ServiceTrackerDto> serviceTrackerDtos = new ArrayList<>();

        trackers.iterator().forEachRemaining(t -> serviceTrackerDtos.add(toDto(t)));

        serviceTrackerDtos.sort(Comparator.comparing(ServiceTrackerDto::getCreatTime));

        return serviceTrackerDtos;
    }

    public static ServiceTrackerDto toDto(ServiceTracker serviceTracker) {

        if (serviceTracker == null) {
            return null;
        }

        String creatTime = TimeConverter.toString(serviceTracker.getCreatedAt());
        String lastUpdateTime = TimeConverter.toString(serviceTracker.getUpdatedAt());

        ServiceTrackerDto serviceTrackerDto = new ServiceTrackerDto();
        serviceTrackerDto.setId(serviceTracker.getId());
        serviceTrackerDto.setStatus(serviceTracker.getStatus().getName());
        String statusUpdateTime = TimeConverter.toString(serviceTracker.getStatusLastChangeTime());
        serviceTrackerDto.setLastStatusUpdateTime(statusUpdateTime);
        serviceTrackerDto.setCreatTime(creatTime);
        serviceTrackerDto.setLastUpdateTime(lastUpdateTime);
        serviceTrackerDto.setName(serviceTracker.getName());
        serviceTrackerDto.setUrl(serviceTracker.getUrl());
        serviceTrackerDto.setUserId(serviceTracker.getUser().getId());

        return serviceTrackerDto;
    }
}
