package com.fanlan.fighterwx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FighterWxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FighterWxApplication.class, args);
    }
}
