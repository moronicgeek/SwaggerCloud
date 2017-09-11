package za.co.moronicgeek.ni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Created by muhammedpatel on 2016/08/06.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class NiApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(
                NiApplication.class, args);
    }


}
