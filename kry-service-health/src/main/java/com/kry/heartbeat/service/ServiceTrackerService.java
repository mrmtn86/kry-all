package com.kry.heartbeat.service;

import com.kry.heartbeat.dao.ServiceTrackerRepository;
import com.kry.heartbeat.dao.UserRepository;
import com.kry.heartbeat.exception.BusinessRuleException;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.converter.ServiceTrackerConverter;
import com.kry.heartbeat.model.dto.ServiceTrackerDto;
import com.kry.heartbeat.model.entity.ServiceTracker;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.model.entity.User;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTrackerService {

    public static final String INVALID_URL = "Invalid Url : ";

    private final ServiceTrackerRepository serviceTrackerRepository;
    private final StatusService statusService;
    private final HttpService httpService;
    private final UserRepository userRepository;

    public ServiceTrackerService(ServiceTrackerRepository serviceTrackerRepository,
                                 StatusService statusService,
                                 HttpService httpService,
                                 UserRepository userRepository) {
        this.serviceTrackerRepository = serviceTrackerRepository;
        this.statusService = statusService;
        this.httpService = httpService;
        this.userRepository = userRepository;
    }

    public List<ServiceTrackerDto> getAll(Long userId) {
        return ServiceTrackerConverter.toDto(serviceTrackerRepository.findByUser_Id(userId));
    }

    public ServiceTrackerDto save(ServiceTrackerDto serviceTrackerDto) throws BusinessRuleException {

        ServiceTracker serviceTracker;
        if (serviceTrackerDto.getId() != null) {
            serviceTracker = serviceTrackerRepository.findById(serviceTrackerDto.getId()).get();
        } else {
            serviceTracker = new ServiceTracker();
            User user = userRepository.findById(serviceTrackerDto.getUserId()).get();
            serviceTracker.setUser(user);
            ServiceTracker serviceTrackerOld = serviceTrackerRepository.findByUrl(serviceTrackerDto.getUrl());
            if (serviceTrackerOld != null && serviceTrackerOld.getUser().getId().equals(user.getId())) {
                throw new BusinessRuleException("this url already saved");
            }
        }

        serviceTracker.setName(serviceTrackerDto.getName());
        String url = serviceTrackerDto.getUrl();

        //get status info for url
        StatusKey statusKey;
        try {
            statusKey = httpService.getUrlStatus(new URL(url));
        } catch (MalformedURLException e) {
            throw new BusinessRuleException(INVALID_URL + url);
        }
        Status status = statusService.findByKey(statusKey);

        if (!status.equals(serviceTracker.getStatus())) {
            serviceTracker.setStatusLastChangeTime(Instant.now());
        }

        serviceTracker.setStatus(status);
        serviceTracker.setUrl(url);

        serviceTracker = serviceTrackerRepository.save(serviceTracker);

        return ServiceTrackerConverter.toDto(serviceTracker);
    }

    public ServiceTrackerDto findById(Long id) {
        Optional<ServiceTracker> optionalServiceTracker = serviceTrackerRepository.findById(id);
        return optionalServiceTracker.map(ServiceTrackerConverter::toDto).orElse(null);
    }


    public void delete(Long trackerId) {
        serviceTrackerRepository.deleteById(trackerId);
    }
}
