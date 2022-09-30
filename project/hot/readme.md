### 时间线
1. 2022-7-11 完成demo编写
2. 2022-7-12 完成class 热部署项目编写
3. 2022-9-30 开始将项目进行Spring native改造
   1. 修改Spring boot版本为2.4.5
   2. 添加Spring native依赖
    ```text
      <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.4.5</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.experimental</groupId>
            <artifactId>spring-native</artifactId>
            <version>0.9.2</version>
        </dependency>
   ```
   3. 