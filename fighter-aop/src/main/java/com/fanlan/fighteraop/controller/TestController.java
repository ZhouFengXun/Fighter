package com.fanlan.fighteraop.controller;

import com.fanlan.fighteraop.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public ResultVO test(String name) {
        return ResultVO.ok().data("data","zhangsan");
    }
}
