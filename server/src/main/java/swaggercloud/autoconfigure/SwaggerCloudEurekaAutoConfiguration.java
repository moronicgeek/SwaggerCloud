package swaggercloud.autoconfigure;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.config.EurekaDiscoveryClientConfigServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import za.co.moronicgeek.spring.swagger.server.annotation.EnableSwaggerCloud;
import za.co.moronicgeek.spring.swagger.server.registration.SwaggerCloudClientRegistrationListener;
@DependsOn( value = { "discoveryClient" })
@ConditionalOnBean(annotation = { EnableSwaggerCloud.class })
@AutoConfigureAfter({SpringSwaggerCloudServer.class,EurekaDiscoveryClientConfigServiceAutoConfiguration.class, EurekaClientAutoConfiguration.class})
@Configuration
@EnableConfigurationProperties
public class SwaggerCloudEurekaAutoConfiguration {

    @Autowired
    private DiscoveryClient discoveryClient;


    @Bean

    public SwaggerCloudClientRegistrationListener swaggerCloudClientRegistrationListener(){
        return new SwaggerCloudClientRegistrationListener(discoveryClient);
    }
}
