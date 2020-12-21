package com.kry.heartbeat.service.schedule;

import com.kry.heartbeat.dao.ServiceTrackerRepository;
import com.kry.heartbeat.model.entity.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class HeartBeatService {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatService.class);
    private final ServiceTrackerRepository serviceTrackerRepository;
    private final AsyncUpdater asyncUpdater;


    public HeartBeatService(ServiceTrackerRepository serviceTrackerRepository, AsyncUpdater asyncUpdater) {
        this.serviceTrackerRepository = serviceTrackerRepository;
        this.asyncUpdater = asyncUpdater;
    }


    @Scheduled(fixedRate = 60000, initialDelay = 60000)
    public void beat() {

        logger.debug("check started");

        Iterable<ServiceTracker> all = serviceTrackerRepository.findAll();

        for (ServiceTracker serviceTracker : all) {
            asyncUpdater.updateStatus(serviceTracker);
        }
        logger.debug("check finished");
    }


}
