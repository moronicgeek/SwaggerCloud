package za.co.discovery.spring.swagger.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.jetty.ServletContextInitializerConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by muhammedpatel on 2016/08/01.
 */
@Configuration
@EnableConfigurationProperties
@AutoConfigureAfter(ServletContextInitializerConfiguration.class)
public class SpringSwaggerCloudServer {

    @Autowired
    private SpringCloudServerProperties properties;

}
