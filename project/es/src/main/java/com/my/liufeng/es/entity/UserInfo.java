package com.my.liufeng.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "user")
public class UserInfo implements Serializable {
    @Id
    // ES中id不能定义为Long？新建SysUser类测试；自动生成id，类型为Long会报错，类型无法转换，手动设置没问题
    // com.my.liufeng.es.EsApplicationTests#findAllSysUser()
    private String id;

    private String username;

    private int age;

    private String address;

    private String school;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
