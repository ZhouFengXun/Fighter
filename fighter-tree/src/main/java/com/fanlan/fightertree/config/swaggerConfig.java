package com.fanlan.fightertree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lenovo
 */
@Configuration
@EnableSwagger2
public class swaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SPRING_WEB)
                .apiInfo(
                        new ApiInfoBuilder().title("tree")
                        .description("实现部门树以及任意树结构的获取")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fanlan.fightertree.controller"))
                .paths(PathSelectors.any())
                .build();

    }

}
