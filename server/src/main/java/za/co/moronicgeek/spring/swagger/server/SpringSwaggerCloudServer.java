package za.co.moronicgeek.spring.swagger.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import za.co.moronicgeek.spring.swagger.server.handler.PrefixHandlerMapping;
import za.co.moronicgeek.spring.swagger.server.properties.SwaggerCloudProperties;
import za.co.moronicgeek.spring.swagger.server.registry.Registry;
import za.co.moronicgeek.spring.swagger.server.resource.SwaggerRegistrationController;
import za.co.moronicgeek.swagger.cloud.model.AdminRoutes;
import za.co.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-cloud-ui/");
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui/");


    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String contextPath = "/";
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
        prefixHandlerMapping.setPrefix(AdminRoutes.CONTEXT.getPath());
        return prefixHandlerMapping;
    }

    @Bean
    public Registry registry() {
        Registry registry = new Registry();
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test").withGroupId("za.co.test1").withSwaggerUrl("http://test")));
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test1").withGroupId("za.co.test1").withSwaggerUrl("http://test")));
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test2").withGroupId("za.co.test2").withSwaggerUrl("http://test")));
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test3").withGroupId("za.co.test2").withSwaggerUrl("http://test")));
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test4").withGroupId("za.co.test3").withSwaggerUrl("http://test")));
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test5").withGroupId("za.co.test3").withSwaggerUrl("http://test")));
        registry.addApi(new ApplicationRegistrationMetadata(ApplicationRegistrationMetadata.create("test6").withGroupId("za.co.test4").withSwaggerUrl("http://test")));

        return registry;
    }

    @Bean
    public SwaggerRegistrationController registrationController() {
        return new SwaggerRegistrationController();
    }
}
