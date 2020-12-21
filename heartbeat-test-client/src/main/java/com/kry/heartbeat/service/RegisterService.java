package com.kry.heartbeat.service;

import com.kry.heartbeat.controller.HeartBeatController;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.kry.heartbeat.Application.PORT;

@Service
public class RegisterService {

    public void registerToHeartbeatService() {
        try {

            URL url = new URL(HeartBeatController.REMOTE_URL + "/service-tracker");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);

            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestMethod("POST");


            String jsonInputString = "{\"name\":\"" + PORT + "\",\"url\":\"http://localhost:" + PORT + "\"}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseMessage = con.getResponseCode();

            System.out.println("response  code : " + responseMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
