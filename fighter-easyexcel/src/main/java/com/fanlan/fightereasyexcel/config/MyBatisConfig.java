package com.fanlan.fightereasyexcel.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan("com.fanlan.fightereasyexcel.mbg.mapper || com.fanlan.fightereasyexcel.dao")
public class MyBatisConfig {
}