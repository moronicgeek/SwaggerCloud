package za.co.moronicgeek.spring.swagger.server;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

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

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        if (!hasConverter(converters, MappingJackson2HttpMessageConverter.class)) {
            ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                    .applicationContext(this.applicationContext).build();
            converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
        }
    }

    private boolean hasConverter(List<HttpMessageConverter<?>> converters,
                                 Class<? extends HttpMessageConverter<?>> clazz) {
        for (HttpMessageConverter<?> converter : converters) {
            if (clazz.isInstance(converter)) {
                return true;
            }
        }
        return false;
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
