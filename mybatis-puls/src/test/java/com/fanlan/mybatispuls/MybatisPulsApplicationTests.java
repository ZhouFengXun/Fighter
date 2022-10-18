package com.fanlan.mybatispuls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fanlan.mybatispuls.mapper.EduSubjectMapper;
import com.fanlan.mybatispuls.pojo.EduSubject;
import com.fanlan.mybatispuls.service.eduSubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MybatisPulsApplicationTests {

    @Resource
    private EduSubjectMapper eduSubjectMapper;


    @Autowired
    private eduSubjectService eduSubjectService;

    @Test
    void contextLoads(){
        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(null);
        System.out.println(eduSubjects);
    }

    @Test
    void contextLoads1(){
        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(null);
        System.out.println(eduSubjects);
    }


    @Test
    void mybatisPils(){
//        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
//        objectQueryWrapper.eq("","");
        EduSubject eduSubject = new EduSubject();
        eduSubject.setTitle("javasssss");
        eduSubject.setSort(1122);
        eduSubject.setGmtCreate(new Date());
        eduSubject.setGmtModified(new Date());
        eduSubject.setParentId("3");



        boolean save = eduSubjectService.save(eduSubject);
        System.out.println(save);
    }
    @Test
    void mybatisPils1(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("title","javasssss");
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        System.out.println(one);
    }

    @Test
    void mybatisPils2(){
        QueryWrapper queryWrapper=new QueryWrapper();

        queryWrapper.ge("sort",1);
        List list = eduSubjectService.list(queryWrapper);
        System.out.println(list);
    }


    @Test
    void test(){

        Page<EduSubject> objectIPage = new Page<>();
        objectIPage.setCurrent(1);
        objectIPage.setSize(2);
        QueryWrapper<EduSubject> objectQueryWrapper = new QueryWrapper<EduSubject>();
        objectQueryWrapper.ge("sort",1);
        Page<EduSubject> page = eduSubjectService.page(objectIPage, objectQueryWrapper);
        System.out.println(page.getRecords());

    }

    @Test
    void mybatis(){

        String str = "code1|code2";
        String str1 = "code2|code1|code3";
        String[] split1 = str1.split("\\|");
        List<String> list = Arrays.asList(split1);
        boolean code1 = list.contains("code4");
        System.out.println("================="+code1);

        String[] split = str.split("\\|");
        System.out.println(Arrays.toString(split));



        System.out.println(Arrays.toString(split1));
        System.out.println(Arrays.equals(split,split1));


        System.out.println();

        System.out.println(str.equals(str1));

//        Random random = new Random();
//        int i = random.nextInt(4);
//        System.out.println(i);

        System.out.println(Math.round((Math.random() + 1) * 1000));

        String s = new StringBuilder().append("client").append(getTimeStr("yyyymmddhhmmss")).append(Math.round((Math.random() + 1) * 1000)).toString();

        System.out.println(s);
//
//        //先用fastjson把json字符串转成数组
//        JSONArray jsonArray1 = JSONArray.parseArray(Arrays.toString(split));
//        JSONArray jsonArray2 = JSONArray.parseArray(Arrays.toString(split1));
//        //对比数组的长度是否相同
//        if (jsonArray1.size() == jsonArray2.size()){
//            //不考虑顺序的情况下对比两个数组是否相同
//            if(jsonArray1.containsAll(jsonArray2)){
//                System.out.println("相同");
//            }else {
//                System.out.println("不相同");
//            }
//        }else {
//            System.out.println("不相同");
//        }
    }

    public static String getTimeStr(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }
}
