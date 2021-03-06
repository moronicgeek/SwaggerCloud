package swaggercloud.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moronicgeek.swagger.cloud.model.AdminRoutes;
import com.github.moronicgeek.swagger.cloud.rest.SwaggerCloudClientRestTemplate;
import com.github.moronicgeek.swagger.cloud.server.annotation.EnableSwaggerCloud;
import com.github.moronicgeek.swagger.cloud.server.handler.PrefixHandlerMapping;
import com.github.moronicgeek.swagger.cloud.server.properties.SwaggerCloudProperties;
import com.github.moronicgeek.swagger.cloud.server.registration.SwaggerCloudClientRegistrationListener;
import com.github.moronicgeek.swagger.cloud.server.registry.Registry;
import com.github.moronicgeek.swagger.cloud.server.resource.SwaggerRegistrationController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * Created by muhammedpatel on 2016/08/01.
 */
@ConditionalOnBean(annotation = {EnableSwaggerCloud.class})
@Configuration
@EnableConfigurationProperties

public class SpringSwaggerCloudServer extends WebMvcConfigurerAdapter
        implements ApplicationContextAware {


    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private ServerProperties server;
    private ApplicationContext applicationContext;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;


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

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/swagger-cloud-ui/");
//        registry.addResourceHandler("/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/swagger-ui/");
//
//
//    }



    @Bean
    public PrefixHandlerMapping prefixHandlerMapping() {
        PrefixHandlerMapping prefixHandlerMapping = new PrefixHandlerMapping(registrationController());
        prefixHandlerMapping.setPrefix(AdminRoutes.CONTEXT.getPath());
        return prefixHandlerMapping;
    }

    @Bean
    public Registry registry() {
        return new Registry();
    }

    @Bean
    public SwaggerCloudClientRegistrationListener swaggerCloudClientRegistrationListener() {
        // LOGGER.info("Registering SwaggerCloudClientRegistrationListener");
        return new SwaggerCloudClientRegistrationListener();

    }

    @Bean
    public SwaggerRegistrationController registrationController() {
        return new SwaggerRegistrationController();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SwaggerCloudClientRestTemplate swaggerCloudClientRestTemplate() {
        return new SwaggerCloudClientRestTemplate(restTemplate());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-cloud-ui/");
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }



}
