package com.fanlan.fightertest.stream;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CmdVo {
    private String cmd;

    private String param;

    private String value;
}
