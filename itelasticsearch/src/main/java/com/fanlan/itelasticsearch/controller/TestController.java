package com.fanlan.itelasticsearch.controller;

import com.fanlan.itelasticsearch.entity.stu;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * elasticsearchRestTemplate.queryForList是查询一个列表，用的就是ElasticsearchRestTemplate的一个对象实例；
 * NativeSearchQuery ：是springdata中的查询条件；
 * NativeSearchQueryBuilder ：用于建造一个NativeSearchQuery查询对象；
 * QueryBuilders ：设置查询条件，是ES中的类；
 * SortBuilders ：设置排序条件；
 * HighlightBuilder ：设置高亮显示；
 */
@RestController
public class TestController {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引
     *
     * @return
     */
    @RequestMapping("/createIndex")
    public String createIndex() {
        boolean b = elasticsearchRestTemplate.indexOps(stu.class).create();
        return b == true ? "succes" : "fail";
    }
    @RequestMapping("/delete")
    public String delete(){
        boolean delete = elasticsearchRestTemplate.indexOps(stu.class).delete();
        return delete == true ? "succes" : "fail";
    }
    /**
     * 添加数据
     */
    @RequestMapping("/addDecoment")
    public void addDecoment() {
        Iterable<stu> save = elasticsearchRestTemplate.save(data());
        System.out.println(save);
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping("/search")
    public Object search() throws NoSuchFieldException {
        //查询全部
        MatchAllQueryBuilder queryBuilder1 = QueryBuilders.matchAllQuery();


        //查询特定字段
        TermQueryBuilder builder = QueryBuilders.termQuery(stu.class.getDeclaredField("stuName").getName()+".keyword","张三");


        Pageable pageable = PageRequest.of(0, 2);

        SortBuilder sortBuilder = new FieldSortBuilder("age")
                .order(SortOrder.ASC);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("鲁班1号");

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                //查询条件
                .withQuery(builder)
                // .withQuery(QueryBuilders.termQuery("stuName", "鲁班1号"))
//                //分页
//                .withPageable(pageable)
                //排序
//                .withSort(sortBuilder)
                .build();
        SearchHits<stu> search = null;
        try {
            search = elasticsearchRestTemplate.search(query, stu.class);
        } catch (Exception e) {
             throw new RuntimeException(e.getMessage());
        }finally {
            System.out.println("我执行了");
        }

        for (SearchHit<stu> stuSearchHit : search) {
            System.out.println(stuSearchHit);
        }
        return search;
    }

    private static List<stu> data(){
        ArrayList<stu> stus = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            stu stu = new stu();
            stu.setId(i + "");
            stu.setStuName("张三");
            stu.setAge(i);
            stu.setDir(new Date());
            stu.setContext("为开发团队选择一款优秀的MVC框架是件难事儿，在众多可行的方案中决择需要很高的经验和水平");
            stu.setAddress("上海");
            stus.add(stu);
        }
        return stus;
    }
}
