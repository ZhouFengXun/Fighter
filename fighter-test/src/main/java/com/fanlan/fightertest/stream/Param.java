package com.fanlan.fightertest.stream;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Param {

    private String cmd;

    private Map<String, String> param;

    private List<CmdVo> param1;
}
