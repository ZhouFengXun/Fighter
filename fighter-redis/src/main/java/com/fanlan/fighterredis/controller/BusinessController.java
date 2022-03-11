package com.fanlan.fighterredis.controller;

import com.alibaba.fastjson.JSONObject;
import com.fanlan.fighterredis.common.AutoIdempotent;
import com.fanlan.fighterredis.common.vo.Result;
import com.fanlan.fighterredis.common.vo.ResultVo;
import com.fanlan.fighterredis.service.TokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BusinessController {

    @Resource
    private TokenService tokenService;


    @PostMapping("/get/token")
    public Result getToken(){
        return tokenService.createToken();
    }


    @AutoIdempotent
    @GetMapping("/test/Idempotence")
    public String testIdempotence() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMessage("接口幂等性测试");
        resultVo.setData(null);
        return JSONObject.toJSONString(resultVo);
    }

}
