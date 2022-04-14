package com.fanlan.fighteraop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FighterAopApplication {

    public static void main(String[] args) {
        //获取容器
        ApplicationContext applicationContext = SpringApplication.run(FighterAopApplication.class, args);
        //获取容器的数量
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);
        //获取bean名称
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }


    }

}
