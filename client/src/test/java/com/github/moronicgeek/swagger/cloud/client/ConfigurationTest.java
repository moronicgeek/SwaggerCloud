package com.github.moronicgeek.swagger.cloud.client;

import org.junit.Assert;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import com.github.moronicgeek.swagger.cloud.model.SwaggerCloudClientProperties;

import static org.hamcrest.Matchers.is;

public class ConfigurationTest {

    private AnnotationConfigWebApplicationContext context;


    public void close() {
        if (this.context != null) {
            this.context.close();
        }
    }


    public void testSwagerCloudAdminProperties(){

        load("swagger.cloud.boot.client.name:IchiServerAPI"," swagger.cloud.boot.client.swagger-url:http://localhost:8081/swagger.json",
                "swagger.cloud.boot.client.groupId:za.co.moronicgeek.ichiserver");
        SwaggerCloudClientProperties clientProperties = new SwaggerCloudClientProperties();
        context.getAutowireCapableBeanFactory().autowireBean(clientProperties);

       Assert.assertThat(clientProperties.getSwaggerUrl(),is("za.co.moronicgeek.ichiserver"));




    }



    private void load(String... environment) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(PropertyPlaceholderAutoConfiguration.class);
        applicationContext.register(SwaggerCloudClientProperties.class);
        applicationContext.register(ServerPropertiesAutoConfiguration.class);
        EnvironmentTestUtils.addEnvironment(applicationContext, environment);
        applicationContext.refresh();
        this.context = applicationContext;
    }
}
