package za.co.moronicgeek.sample.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.moronicgeek.spring.swagger.server.EnableSwaggerCloud;

/**
 * Created by muhammedpatel on 2016/08/07.
 */
@SpringBootApplication
@EnableSwaggerCloud
public class ServerApplication {

    public static void main(String [] args){
        SpringApplication.run(ServerApplication.class);
    }
}
