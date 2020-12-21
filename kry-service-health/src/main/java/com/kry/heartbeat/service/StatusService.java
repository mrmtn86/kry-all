package com.kry.heartbeat.service;

import com.kry.heartbeat.dao.StatusRepository;
import com.kry.heartbeat.exception.BusinessRuleException;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.Status;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    public static final String UNKNOWN_STATUS = "Unknown Status : ";
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Cacheable("status_by_key")
    public Status findByKey(StatusKey key) throws BusinessRuleException {
        Status status = statusRepository.findByKey(key);
        if(status == null){
            throw new BusinessRuleException(UNKNOWN_STATUS + key);
        }
        return status;
    }
}
