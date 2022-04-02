package com.fanlan.fighterdemo.design.strategyAndEume.pojo;

import lombok.Data;

@Data
public class Stock {
    // 股票交易代码
    private String code;

    // 现价
    private Double price;

    // 涨幅
    private Double rise;

    public Stock() {
    }

    public Stock(String code, Double price, Double rise) {
        this.code = code;
        this.price = price;
        this.rise = rise;
    }
}
