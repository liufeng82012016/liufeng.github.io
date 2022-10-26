package com.my.liufeng.es;

import com.my.liufeng.es.dao.SysUserDao;
import com.my.liufeng.es.entity.SysUser;
import com.my.liufeng.es.util.ChineseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Iterator;

@SpringBootTest
class EsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private SysUserDao sysUserDao;

    @Test
    public void testDefault() {
        deleteSysUser();
        addSysUserDefault();
        findAllSysUser();
    }

    @Test
    public void testLong() {
        deleteSysUser();
        addSysUserLong();
        findAllSysUser();
    }

    @Test
    public void addSysUserLong() {
        SysUser userInfo = new SysUser();
        userInfo.setId(1L);
        userInfo.setAddress(ChineseUtil.randomProvince());
        userInfo.setUsername(ChineseUtil.getRandomChineseName());
        userInfo.setAge(ChineseUtil.RANDOM.nextInt(30) + 5);
        userInfo.setSchool(ChineseUtil.randomSchool());
        sysUserDao.save(userInfo);
    }

    @Test
    public void addSysUserDefault() {
        SysUser userInfo = new SysUser();
        userInfo.setAddress(ChineseUtil.randomProvince());
        userInfo.setUsername(ChineseUtil.getRandomChineseName());
        userInfo.setAge(ChineseUtil.RANDOM.nextInt(30) + 5);
        userInfo.setSchool(ChineseUtil.randomSchool());
        sysUserDao.save(userInfo);
    }

    @Test
    public void findAllSysUser() {
        // 如果未手动设置id为long类型，会报错，id无法转换为String
        Iterator<SysUser> sysUserIterator = sysUserDao.findAll().iterator();
        while (sysUserIterator.hasNext()) {
            SysUser next = sysUserIterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void deleteSysUser() {
        sysUserDao.deleteAll();
    }

}
