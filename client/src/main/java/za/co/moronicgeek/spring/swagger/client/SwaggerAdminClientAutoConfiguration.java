package za.co.moronicgeek.spring.swagger.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import za.co.moronicgeek.spring.swagger.client.listener.RegistrationApplicationListener;
import za.co.moronicgeek.spring.swagger.client.properties.SwaggerCloudAdminProperties;
import za.co.moronicgeek.spring.swagger.client.properties.SwaggerCloudClientProperties;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@Configuration
@EnableConfigurationProperties({SwaggerCloudAdminProperties.class, SwaggerCloudClientProperties.class})
public class SwaggerAdminClientAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerAdminClientAutoConfiguration.class);
    @Autowired
    private SwaggerCloudClientProperties clientProperties;

    @Autowired
    private SwaggerCloudAdminProperties adminProperties;


    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationRegistrationService registrationBean() {
        LOGGER.info("Registering ApplicationRegistrationBean");
        builder.messageConverters(new MappingJackson2HttpMessageConverter());
        return new ApplicationRegistrationService(clientProperties, adminProperties, builder.build());

    }

    @Bean
    @ConditionalOnMissingBean
    public RegistrationApplicationListener applicationListener(ApplicationRegistrationService bean) {
        LOGGER.info("Registering ApplicationRegistrationService");
        RegistrationApplicationListener listener = new RegistrationApplicationListener(bean);
        return listener;

    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**/swagger.json").allowedOrigins("*");
            }
        };
    }
}
