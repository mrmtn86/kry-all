package com.kry.heartbeat.service;

import com.kry.heartbeat.dao.StatusRepository;
import com.kry.heartbeat.dao.UserRepository;
import com.kry.heartbeat.model.StatusKey;
import com.kry.heartbeat.model.entity.Status;
import com.kry.heartbeat.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public DataLoader(UserRepository userRepository, StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) {

        List<String> users = Arrays.asList("nelly", "ludvig", "kerry", "metin");
        users.forEach(uName -> {
            String email = uName.toLowerCase() + "@kry.com";

            if (userRepository.findByEmail(email) == null) {
                User user = new User(uName, email);
                userRepository.save(user);
            }
        });

        for (StatusKey key : StatusKey.values()) {
            if (statusRepository.findByKey(key) == null) {
                statusRepository.save(new Status(key.toString(), key));
            }
        }


        for (User user : userRepository.findAll()) {
            logger.debug(user.toString());
        }


        logger.info("Data loaded successfully");
    }
}
