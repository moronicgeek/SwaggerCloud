package za.co.moronicgeek.sample.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import za.co.moronicgeek.spring.swagger.server.annotation.EnableSwaggerCloud;

/**
 * Created by muhammedpatel on 2016/08/07.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwaggerCloud
public class ServerApplication {

    public static void main(String [] args){
        SpringApplication.run(ServerApplication.class);
    }
}
