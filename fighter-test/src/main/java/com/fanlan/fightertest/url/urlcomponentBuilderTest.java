package com.fanlan.fightertest.url;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class urlcomponentBuilderTest {


    private final String url = "http://localhost:8080/getData";


    @Resource
    RestTemplate restTemplate;


    @PostMapping("/show")
    public String show(String uid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("system", "005");
        map.put("channll", "01");


        //添加请求参数
        HttpHeaders httpHeaders = new HttpHeaders();

        //设置属性   httpHeaders.set("");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity httpEntity = new HttpEntity("null", httpHeaders);

        //组建url + 参数
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            uriComponentsBuilder.queryParam(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        String Urlbuild = uriComponentsBuilder.build().toString();

        System.out.println(Urlbuild);

        // 请求接口
        ResponseEntity<String> exchange = restTemplate.exchange(Urlbuild, HttpMethod.GET, httpEntity, String.class);
        System.out.println(exchange);
        /*
        * <200,success,[Content-Type:"text/plain;charset=UTF-8", Content-Length:"7",
        * Date:"Wed, 22 Jun 2022 04:26:21 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        * */
        String body = exchange.getBody();
        System.out.println(body);
        return body;

    }

    @GetMapping("/getData")
    public String getData() {
        return "success";
    }
}
