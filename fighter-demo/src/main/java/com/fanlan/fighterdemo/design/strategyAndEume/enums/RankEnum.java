package com.fanlan.fighterdemo.design.strategyAndEume.enums;


import com.fanlan.fighterdemo.design.strategyAndEume.pojo.Stock;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;




public enum RankEnum {
    // 以下三个为策略实例
    HighPrice {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream()
                    .sorted(Comparator.comparing(Stock::getPrice).reversed())
                    .collect(Collectors.toList());

        }
    },
    LowPrice {
        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream()
                    .sorted(Comparator.comparing(Stock::getPrice))
                    .collect(Collectors.toList());
        }
    },
    HighRise {


        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream()
                    .sorted(Comparator.comparing(Stock::getRise).reversed())
                    .collect(Collectors.toList());
        }
    },
    HighCode{


        @Override
        public List<Stock> sort(List<Stock> source) {
            return source.stream()
                    .sorted(Comparator.comparing(Stock::getCode).reversed())
                    .collect(Collectors.toList());
        }
    };

    // 这里定义了策略接口
    public abstract List<Stock> sort(List<Stock> source);

}
