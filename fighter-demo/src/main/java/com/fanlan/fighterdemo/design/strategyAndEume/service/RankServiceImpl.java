package com.fanlan.fighterdemo.design.strategyAndEume.service;

import com.fanlan.fighterdemo.design.strategyAndEume.enums.RankEnum;
import com.fanlan.fighterdemo.design.strategyAndEume.pojo.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RankServiceImpl {

    /**
     * dataService.getSource() 提供原始的股票数据
     */
    //@Resource
    //private DataService dataService;
    private static List list=new ArrayList(Arrays.asList("3","1","12"));




    /**
     * 前端传入榜单类型, 返回排序完的榜单
     *
     * @param rankType 榜单类型 形似 RankEnum.HighPrice.name()
     * @return 榜单数据
     */
    public List<Stock> getRank(String rankType) {
        // 获取策略，这里如果未匹配会抛 IllegalArgumentException异常
        RankEnum rank = RankEnum.valueOf(rankType);
        // 然后执行策略
        return rank.sort(list);
    }

    public static void main(String[] args) {

        Stock stock = new Stock("1",1.0,2.0);
        Stock stock1 = new Stock("2",2.0,3.0);
        Stock stock2 = new Stock("3",3.0,4.0);
        List<Stock> list=new ArrayList();
        list.add(stock);
        list.add(stock1);
        list.add(stock2);


        RankEnum highRise = RankEnum.valueOf("LowPrice");
        List sort = highRise.sort(list);
        System.out.println(sort);


    }
}
