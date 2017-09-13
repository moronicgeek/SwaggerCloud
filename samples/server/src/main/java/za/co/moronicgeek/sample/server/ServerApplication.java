package za.co.moronicgeek.sample.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import za.co.moronicgeek.spring.swagger.server.annotation.EnableSwaggerCloud;

/**
 * Created by muhammedpatel on 2016/08/07.
 */
@SpringBootApplication
@EnableWebMvc
@EnableDiscoveryClient
@EnableSwaggerCloud
@Configuration
public class ServerApplication {

    public static void main(String [] args){
        SpringApplication.run(ServerApplication.class);
    }


}
