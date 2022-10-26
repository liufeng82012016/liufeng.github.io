package com.my.liufeng.es.dao;

import com.my.liufeng.es.entity.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends ElasticsearchRepository<UserInfo, String> {
}
