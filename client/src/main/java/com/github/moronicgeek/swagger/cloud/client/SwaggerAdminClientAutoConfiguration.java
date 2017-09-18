package com.github.moronicgeek.swagger.cloud.client;

import com.github.moronicgeek.swagger.cloud.client.listener.RegistrationApplicationListener;
import com.github.moronicgeek.swagger.cloud.client.resource.SwaggerCloudClientResource;
import com.github.moronicgeek.swagger.cloud.model.SwaggerCloudAdminProperties;
import com.github.moronicgeek.swagger.cloud.model.SwaggerCloudClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaAutoServiceRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
    @ConditionalOnMissingBean(EurekaAutoServiceRegistration.class)
    public ApplicationRegistrationService registrationBean() {
        LOGGER.info("Registering ApplicationRegistrationBean");
        builder.messageConverters(new MappingJackson2HttpMessageConverter());
        return new ApplicationRegistrationService(clientProperties, adminProperties, builder.build());

    }
    @Bean
    @ConditionalOnBean(EurekaAutoServiceRegistration.class)
    public SwaggerCloudClientResource swaggerCloudClientResource() {
        LOGGER.info("Registering SwaggerCloudClientResource");
        return new SwaggerCloudClientResource();
    }

    @Bean
    @ConditionalOnMissingBean(EurekaAutoServiceRegistration.class)
    public RegistrationApplicationListener applicationListener(ApplicationRegistrationService bean) {
        LOGGER.info("Registering ApplicationRegistrationService");
        RegistrationApplicationListener listener = new RegistrationApplicationListener(bean);
        return listener;

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Allow anyone and anything access. Probably ok for Swagger spec
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/v2/api-docs", config);
        return new CorsFilter(source);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**/swagger.json").allowedOrigins("*");
                registry.addMapping("/**/api-docs").allowedOrigins("*");
            }
        };
    }
}
