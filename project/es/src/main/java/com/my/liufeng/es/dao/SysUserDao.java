package com.my.liufeng.es.dao;

import com.my.liufeng.es.entity.SysUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends ElasticsearchRepository<SysUser, Long> {
}
