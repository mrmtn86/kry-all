package com.kry.heartbeat.controller;

import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = HeartBeatController.REMOTE_URL)
@RequestMapping
public class HeartBeatController {

    public static final String REMOTE_URL = "http://localhost:8080";

    @GetMapping
    public String check()  {
        return "OK";
    }
}
