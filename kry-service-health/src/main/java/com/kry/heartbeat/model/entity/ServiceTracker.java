package com.kry.heartbeat.model.entity;


import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "user_url_unique", columnNames = {"user_id", "url"}))
public class ServiceTracker extends BaseEntity {

    @ManyToOne(optional = false)
    private User user;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @URL(protocol = "http")
    private String url;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Status status;
    Instant statusLastChangeTime;

    public ServiceTracker() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getStatusLastChangeTime() {
        return statusLastChangeTime;
    }

    public void setStatusLastChangeTime(Instant satusLastChangeTime) {
        this.statusLastChangeTime = satusLastChangeTime;
    }

    @Override
    public String toString() {
        return "ServiceTracker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
