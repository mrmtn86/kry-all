package com.kry.heartbeat;

import com.kry.heartbeat.controller.HeartBeatController;
import com.kry.heartbeat.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Random;


@SpringBootApplication
public class Application {

    public static String PORT;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {


        if (args == null || args.length == 0) {
            PORT = String.valueOf((new Random().nextInt(100) + 8081));
        } else {
            PORT = args[0];
        }


        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", PORT));
        app.run(args);

        logger.info("server started check : http://localhost:" + PORT);
    }

    @Bean
    public void register( ) {

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

