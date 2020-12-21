package com.kry.heartbeat.dao;

import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
     Status findByKey(StatusKey key);
}
