package com.kry.heartbeat.model.dto;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class ServiceTrackerDto {
    public static final String NAME_REQUIRED = "name required";
    public static final String USER_REQUIRED = "user required";
    public static final String URL_REQUIRED = "url required";
    private Long id;
    @ApiModelProperty(value = "service tracker name")
    @NotNull(message = NAME_REQUIRED)
    private String name;
    @ApiModelProperty(value = "service url")
    @NotNull(message = URL_REQUIRED)
    private String url;
    @ApiModelProperty(value = "service status")
    private String status;
    @ApiModelProperty(value = "the last date the tracker was modified")
    private String lastUpdateTime;
    @ApiModelProperty(value = "the date the tracker was created")
    private String creatTime;
    @ApiModelProperty(value = "the last date the tracker's status was changed")
    private String lastStatusUpdateTime;
    @ApiModelProperty(value = "the creator id of the tracker")
    @NotNull(message = USER_REQUIRED)
    private Long userId;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastStatusUpdateTime(String lastStatusUpdateTime) {
        this.lastStatusUpdateTime = lastStatusUpdateTime;
    }

    public String getLastStatusUpdateTime() {
        return lastStatusUpdateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
