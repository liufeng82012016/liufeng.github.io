## Spring native
1. github:(https://github.com/spring-projects-experimental/spring-native)
2. 官网：https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-buildpacks


### 编译镜像

#### SpringBoot web+MySQL+MyBatis
1. 编写代码，运行正常
2. 修改pom.xml
```text
        <dependency>
            <groupId>org.springframework.experimental</groupId>
            <artifactId>spring-native</artifactId>
            <version>${spring-native.version}</version>
        </dependency>
        
        
          <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <classifier>${repackage.classifier}</classifier>
                    <image>
                        <!--启用原生镜像支持 通过环境变量BP_NATIVE_IMAGE启用-->
                        <builder>paketobuildpacks/builder:tiny</builder>
                        <env>
                            <BP_NATIVE_IMAGE>true</BP_NATIVE_IMAGE>
                        </env>
                    </image>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.experimental</groupId>
                <artifactId>spring-aot-maven-plugin</artifactId>
                <version>${spring-native.version}</version>
                <executions>
                    <execution>
                        <id>test-generate</id>
                        <goals>
                            <goal>test-generate</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>generate</id>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <-- hibernate才需要加 -->
            <plugin>
                <groupId>org.hibernate.orm.tooling</groupId>
                <artifactId>hibernate-enhance-maven-plugin</artifactId>
                <version>5.6.11.Final</version>
                <executions>
                    <execution>
                        <configuration>
                            <failOnError>true</failOnError>
                            <enableLazyInitialization>true</enableLazyInitialization>
                            <enableDirtyTracking>true</enableDirtyTracking>
                            <enableAssociationManagement>true</enableAssociationManagement>
                            <enableExtendedEnhancement>false</enableExtendedEnhancement>
                        </configuration>
                        <goals>
                            <goal>enhance</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    <repositories>
        <repository>
            <!--构建Spring Native依赖所必须的库-->
            <id>spring-releases</id>
            <name>Spring Releases</name>
            <url>https://repo.spring.io/release</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <!--构建Spring Native 插件库-->
            <id>spring-releases</id>
            <name>Spring Releases</name>
            <url>https://repo.spring.io/release</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

    <profiles>
        <profile>
            <id>native</id>
            <properties>
                <repackage.classifier>exec</repackage.classifier>
                <native-buildtools.version>0.9.13</native-buildtools.version>
            </properties>
            <dependencies>
                <dependency>
                    <groupId>org.junit.platform</groupId>
                    <artifactId>junit-platform-launcher</artifactId>
                    <scope>test</scope>
                </dependency>
            </dependencies>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.graalvm.buildtools</groupId>
                        <artifactId>native-maven-plugin</artifactId>
                        <version>${native-buildtools.version}</version>
                        <extensions>true</extensions>
                        <executions>
                            <execution>
                                <id>test-native</id>
                                <phase>test</phase>
                                <goals>
                                    <goal>test</goal>
                                </goals>
                            </execution>
                            <execution>
                                <id>build-native</id>
                                <phase>package</phase>
                                <goals>
                                    <goal>build</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
```
3. 打包
   1. 运行 mvn spring-boot:build-image命令
   2. 点击右侧package
4. 报错 Unsatisfied dependency expressed through field 'baseMapper'
5. 然后点击HELP.md，发现：The following dependency is not known to work with Spring Native: 'MyBatis Framework'. As a result, your application may not work as expected


#### SpringBoot web+MySQL+JPA
1. 增加JPA依赖，编写代码，运行正常
2. 打包报错：找不到MyBatis.BaseMapper类。奇怪，命名没有引用，为什么还会测试报错？？clean掉历史，重新打包，成功
3. 然后怎么执行呢？
