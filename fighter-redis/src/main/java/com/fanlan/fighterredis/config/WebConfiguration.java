package com.fanlan.fighterredis.config;

import com.fanlan.fighterredis.common.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * WebMvcConfigurerAdapter
 * spring 4.xx（或者更低）版本升级到Spring 5.xx以及将Spring Boot 1.xx版本升级到Spring Boot 2.xx版本后会报的一个严重警告：
 * "Warning:The type WebMvcConfigurerAdapter is deprecated."
 *
 *
 * 解决方法 直接 实现 WebMvcConfigurer 接口
 * WebMvcConfigurerAdapter 同样也是实现 WebMvcConfigurer 接口的实现类，就不需要super()接口的方法
 */
@Configuration
//public class WebConfiguration extends WebMvcConfigurerAdapter {
//
//    @Resource
//    private AutoIdempotentInterceptor autoIdempotentInterceptor;
//
//    /**
//     * 添加拦截器
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //添加拦截器
//        registry.addInterceptor(autoIdempotentInterceptor);
//        super.addInterceptors(registry);
//    }
//}
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 静态资源过滤
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").
                addResourceLocations("file:F:/");
        super.addResourceHandlers(registry);
    }

    /**
     * 跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/cors/**")
                .allowedHeaders("*")
                .allowedMethods("POST","GET")
                .allowedOrigins("*");
        super.addCorsMappings(registry);
    }
}