package com.kry.heartbeat.service;

import com.kry.heartbeat.exception.BusinessRuleException;
import com.kry.heartbeat.model.StatusKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class HttpService {
    private static final Logger logger = LoggerFactory.getLogger(HttpService.class);

    public static final String PROTOCOL_EXCEPTION_FOR = "ProtocolException for : ";

    public static final String IOEXCEPTION_FOR = "IOException for : ";

    //todo duzenle catch
    public StatusKey getUrlStatus(URL url) {
        StatusKey currentStatusKey = StatusKey.UNKNOWN;
        try {

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(1000);
            con.setReadTimeout(1000);
            con.setRequestMethod("GET");

            int responseStatus = con.getResponseCode();

            if (responseStatus == 200) {
                currentStatusKey = StatusKey.OK;
            }

        } catch (ProtocolException e) {
            currentStatusKey = StatusKey.FAIL;
            logger.debug(PROTOCOL_EXCEPTION_FOR + url.getPath(), e);
        } catch (IOException e) {
            currentStatusKey = StatusKey.FAIL;
            logger.debug(IOEXCEPTION_FOR + url.getPath(), e);
        } finally {
            logger.debug(url.getPath() + " -> " + currentStatusKey);
        }
        return currentStatusKey;
    }
}
