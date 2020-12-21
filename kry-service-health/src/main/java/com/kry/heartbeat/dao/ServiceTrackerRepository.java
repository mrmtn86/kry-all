package com.kry.heartbeat.dao;

import com.kry.heartbeat.model.entity.ServiceTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTrackerRepository extends JpaRepository<ServiceTracker, Long> {
    List<ServiceTracker> findByUser_Id(Long userId);

    ServiceTracker findByUrl(String url);
}
