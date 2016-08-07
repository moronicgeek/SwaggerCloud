package za.co.discovery.spring.swagger.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@Configuration
@EnableConfigurationProperties({SwaggerCloudAdminProperties.class, SwaggerCloudClientProperties.class})
public class SwaggerAdminClientAutoConfiguration {

    @Autowired
    private SwaggerCloudClientProperties clientProperties;

    @Autowired
    private SwaggerCloudAdminProperties adminProperties;


    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationRegistrationBean registrationBean(){
        builder.messageConverters(new MappingJackson2HttpMessageConverter());
        return new ApplicationRegistrationBean(clientProperties,adminProperties,builder.build());

    }
}
