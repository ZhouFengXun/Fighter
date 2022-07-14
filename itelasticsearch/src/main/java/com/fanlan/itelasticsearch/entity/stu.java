package com.fanlan.itelasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @Document指定实体类和索引对应关系 indexName：索引名称    写了这个之后，有就用，没有就创建这个索引
 * type: 索引类型
 * shards: 主分片数量
 * replicas：复制分片数量
 * @Id 指定主键
 * @Field指定普通属性 type： 对应Elasticsearch中属性类型。使用FiledType枚举可以快速获取。测试发现没有type属性可能出现无法自动创建类型问题，所以一定要有type属性。
 * text类型能被分词
 * keywords不能被分词
 * <p>
 * <p>
 * index： 是否创建索引。作为搜索条件时index必须为true
 * analyzer：指定分词器类型。
 */
@Data
@Document(indexName = "stu_index", shards = 2, replicas = 1)
public class stu {

    @Id
    private String id;
    /**
     * Keyword 不会被分词
     */
    @Field(type = FieldType.Keyword)
    private String stuName;


    private Integer age;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date dir;
    /**
     * text  会被分词
     */
    @Field(type = FieldType.Keyword)
    private String context;

    @Field(type = FieldType.Keyword)
    private String address;

    public static void main(String[] args) throws Exception {
        //获取class
        Class<stu> stuClass = stu.class;
        //实例化对象
        stu stu = stuClass.newInstance();
        System.out.println(stu);
        //返回完整类名带包名
        String name = stuClass.getName();
        System.out.println(name);
        //返回返回类名
        String simpleName = stuClass.getSimpleName();
        System.out.println(simpleName);

        //返回类中public修饰的属性
        java.lang.reflect.Field[] fields = stuClass.getFields();
        System.out.println(Arrays.toString(fields).toString());
        //返回类中所有的属性
        java.lang.reflect.Field[] declaredFields = stuClass.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
        //根据属性名name获取指定的属性
        String stuName1 = stuClass.getDeclaredField("stuName").getName();
        System.out.println(stuName1);

        Method[] declaredMethods = stuClass.getDeclaredMethods();
        System.out.println(Arrays.toString(declaredMethods));

        String setStuName1 = stuClass.getDeclaredMethod("setStuName", String.class).getName();
        System.out.println(setStuName1);

        Method setStuName = stuClass.getDeclaredMethod("setStuName", String.class);
        Object lihua = setStuName.invoke(stu, "lihua");
        System.out.println(lihua);


        Class<? extends com.fanlan.itelasticsearch.entity.stu> aClass = stu.getClass();
        com.fanlan.itelasticsearch.entity.stu stu1 = aClass.newInstance();
        System.out.println(stu1);

        System.out.println("-------------------------------------------------");
        Method show = stuClass.getDeclaredMethod("show", String.class);
        Object zhangsan = show.invoke(stu, "zhangsan");
        System.out.println(zhangsan);

    }

    public String show(String name){
        return name;
    }
}

