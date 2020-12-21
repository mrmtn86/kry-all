package com.kry.heartbeat.model.entity;

import com.kry.heartbeat.model.StatusKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class Status extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    private StatusKey key;

    public Status() {
    }

    public Status(String name, StatusKey key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusKey getKey() {
        return key;
    }

    public void setKey(StatusKey key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return key == status.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
