package com.kry.heartbeat.service;

import com.kry.heartbeat.model.StatusKey;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

class HttpServiceTest {

    @Test
    void getUrlStatusShouldReturnOk() throws MalformedURLException {
        StatusKey statusKey = new HttpService().getUrlStatus(new URL("http://google.com"));

        assertThat(statusKey).isEqualTo(StatusKey.OK);
    }
}
