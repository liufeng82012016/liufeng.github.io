package com.my.liufeng.es.controller;

import com.my.liufeng.es.dao.UserDao;
import com.my.liufeng.es.entity.UserInfo;
import com.my.liufeng.es.util.ChineseUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/es")
public class EsController {
    @Resource
    private UserDao userDao;

    @Resource
    private ElasticsearchRestTemplate esRestTemplate;

    @GetMapping("/importUsers")
    public Object insert() {
        List<UserInfo> users = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAddress(ChineseUtil.randomProvince());
            userInfo.setUsername(ChineseUtil.getRandomChineseName());
            userInfo.setAge(ChineseUtil.RANDOM.nextInt(30) + 5);
            userInfo.setSchool(ChineseUtil.randomSchool());
            users.add(userInfo);
            System.out.println(userInfo);
        }
        System.out.println("userDao: " + userDao);
        userDao.saveAll(users);
        return null;
    }

    @GetMapping("/nameQuery")
    public Object nameQuery(HttpServletRequest request, HttpServletResponse response) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("username", request.getParameter("username")))
                .build();
        return esRestTemplate.search(query, UserInfo.class);
    }

    @GetMapping("/strQuery")
    public Object strQuery(HttpServletRequest request, HttpServletResponse response) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("info.like", request.getParameter("str")))
                .build();
        return esRestTemplate.search(query, UserInfo.class);
    }
}
