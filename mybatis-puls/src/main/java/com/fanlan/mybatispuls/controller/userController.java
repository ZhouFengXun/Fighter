package com.fanlan.mybatispuls.controller;


import com.fanlan.mybatispuls.pojo.EduSubject;
import com.fanlan.mybatispuls.service.eduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {


    @Autowired
    private eduSubjectService eduSubjectService;



    @PostMapping
    public void getUser(String id){
        EduSubject byId = eduSubjectService.getById(id);
        System.out.println(byId);
    }

}
