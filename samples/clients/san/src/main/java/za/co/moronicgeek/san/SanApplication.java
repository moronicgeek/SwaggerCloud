package za.co.moronicgeek.san;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by muhammedpatel on 2016/08/06.
 */

@SpringBootApplication
public class SanApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(
                SanApplication.class, args);
    }



}
