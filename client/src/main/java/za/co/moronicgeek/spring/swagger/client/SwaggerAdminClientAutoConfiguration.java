package za.co.moronicgeek.spring.swagger.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import za.co.moronicgeek.spring.swagger.client.listener.RegistrationApplicationListener;
import za.co.moronicgeek.spring.swagger.client.properties.SwaggerCloudAdminProperties;
import za.co.moronicgeek.spring.swagger.client.properties.SwaggerCloudClientProperties;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@Configuration
@EnableConfigurationProperties({SwaggerCloudAdminProperties.class, SwaggerCloudClientProperties.class})
public class SwaggerAdminClientAutoConfiguration {

    private ApplicationContext context;

    @Autowired
    private SwaggerCloudClientProperties clientProperties;

    @Autowired
    private SwaggerCloudAdminProperties adminProperties;


    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationRegistrationService registrationBean(){
        builder.messageConverters(new MappingJackson2HttpMessageConverter());
        return new ApplicationRegistrationService(clientProperties,adminProperties,builder.build());

    }

    @Bean
    @ConditionalOnMissingBean
    public RegistrationApplicationListener applicationListener(ApplicationRegistrationService bean){
        RegistrationApplicationListener listener = new RegistrationApplicationListener(bean);
        return listener;

    }
}
