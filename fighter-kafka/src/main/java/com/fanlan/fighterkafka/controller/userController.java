package com.fanlan.fighterkafka.controller;


import com.fanlan.fighterkafka.producer.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @Autowired
    UserLogProducer userLogProducer;

    @GetMapping("/send")
    public void send(){
        userLogProducer.sendLog();
    }

}
