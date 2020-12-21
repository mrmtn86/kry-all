package com.kry.heartbeat.service.schedule;

import com.kry.heartbeat.dao.ServiceTrackerRepository;
import com.kry.heartbeat.exception.BusinessRuleException;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.ServiceTracker;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.service.HttpService;
import com.kry.heartbeat.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;

@Service
public class AsyncUpdater {
    private static final Logger logger = LoggerFactory.getLogger(AsyncUpdater.class);
    private final StatusService statusService;
    private final HttpService httpService;
    private final ServiceTrackerRepository serviceTrackerRepository;

    public AsyncUpdater(StatusService statusService, HttpService httpService, ServiceTrackerRepository serviceTrackerRepository) {
        this.statusService = statusService;
        this.httpService = httpService;
        this.serviceTrackerRepository = serviceTrackerRepository;
    }

    @Async
    public void updateStatus(ServiceTracker serviceTracker) {

        StatusKey currentStatusKey;
        try {
            currentStatusKey = httpService.getUrlStatus(new URL(serviceTracker.getUrl()));
        } catch (MalformedURLException e) {
            currentStatusKey = StatusKey.FAIL;
            logger.warn("invalid url for tracker : " + serviceTracker.getId() + " url: " + serviceTracker.getUrl());
        }

        StatusKey lastKnownStatusKey = serviceTracker.getStatus().getKey();

        if (!lastKnownStatusKey.equals(currentStatusKey)) {

            Status status = null;
            try {
                status = statusService.findByKey(currentStatusKey);
            } catch (BusinessRuleException e) {
                logger.warn("error while getting status key", e);
            }
            serviceTracker.setStatus(status);
            serviceTracker.setStatusLastChangeTime(Instant.now());
        }
        serviceTrackerRepository.save(serviceTracker);

    }
}
