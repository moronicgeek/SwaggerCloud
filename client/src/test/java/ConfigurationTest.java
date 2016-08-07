import org.junit.Assert;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import za.co.discovery.spring.swagger.client.SwaggerCloudClientProperties;

import static org.hamcrest.Matchers.is;
/**
 * Created by muhammedpatel on 2016/08/06.
 */
public class ConfigurationTest {

    private AnnotationConfigWebApplicationContext context;


    public void close() {
        if (this.context != null) {
            this.context.close();
        }
    }


    public void testSwagerCloudAdminProperties(){
        load("adminServerUrl=IAMAURL","swaggerUrl=IAMASWAGGERURL","name=IHAVEANAME");
        SwaggerCloudClientProperties clientProperties = new SwaggerCloudClientProperties();
        context.getAutowireCapableBeanFactory().autowireBean(clientProperties);

       Assert.assertThat(clientProperties.getSwaggerUrl(),is("IAMASWAGGERURL"));




    }



    private void load(String... environment) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(PropertyPlaceholderAutoConfiguration.class);
        applicationContext.register(ServerPropertiesAutoConfiguration.class);
        EnvironmentTestUtils.addEnvironment(applicationContext, environment);
        applicationContext.refresh();
        this.context = applicationContext;
    }
}
