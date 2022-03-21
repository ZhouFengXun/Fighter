package com.fanlan.fightertree.controller;


import com.fanlan.fightertree.common.Result;
import com.fanlan.fightertree.entity.myDept;
import com.fanlan.fightertree.service.myDeptService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 */
@RestController
@Api
@RequestMapping("/api")
public class myDeptController {

    @Resource
    myDeptService myDeptService;

    @PostMapping("/tree")
    public Result treeData(){
        List<myDept> treeData = myDeptService.getTreeData();
        return  Result.ok(treeData);
    }

}
