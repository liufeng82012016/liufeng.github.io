package com.crossoverjie.cim.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author crossoverJie
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CIMServerApplication implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(CIMServerApplication.class);

//	@Autowired
//	private AppConfiguration appConfiguration ;

    @Value("${server.port}")
    private int httpPort;

    public static void main(String[] args) {
        SpringApplication.run(CIMServerApplication.class, args);
        LOGGER.info("启动 Server 成功");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}