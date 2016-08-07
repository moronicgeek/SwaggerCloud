package za.co.moronicgeek.spring.swagger.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by muhammedpatel on 2016/08/01.
 */
@Configuration
@EnableConfigurationProperties


public class SpringSwaggerCloudServer extends WebMvcConfigurerAdapter
        implements ApplicationContextAware {


    @Autowired
    private ServerProperties server;

    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Autowired
    private RestTemplateBuilder builder;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }


    @Bean
    @ConditionalOnMissingBean
    public SwaggerCloudProperties adminServerProperties() {
        return new SwaggerCloudProperties();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String contextPath = "/test";
        if (StringUtils.hasText(contextPath)) {
            registry.addRedirectViewController(contextPath,
                    server.getPath(contextPath) + "/");
        }
        registry.addViewController(contextPath + "/")
                .setViewName("forward:index.html");
    }

    @Bean
    public PrefixHandlerMapping prefixHandlerMapping() {
        PrefixHandlerMapping prefixHandlerMapping = new PrefixHandlerMapping(registrationController());

        prefixHandlerMapping.setPrefix("test");
        return prefixHandlerMapping;
    }

    @Bean
    public SwaggerRegistrationController registrationController(){
        return new SwaggerRegistrationController();
    }
}
