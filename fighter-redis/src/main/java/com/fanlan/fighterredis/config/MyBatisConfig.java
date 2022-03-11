package com.fanlan.fighterredis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.fanlan.fighterredis.mapper")
public class MyBatisConfig {
}
