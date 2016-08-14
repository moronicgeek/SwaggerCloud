package za.co.moronicgeek.spring.swagger.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import za.co.moronicgeek.spring.swagger.client.properties.SwaggerCloudAdminProperties;
import za.co.moronicgeek.spring.swagger.client.properties.SwaggerCloudClientProperties;

/**
 * Created by muhammedpatel on 2016/08/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationRegistrationServiceTest {


    @InjectMocks

    ApplicationRegistrationService service;
@Mock
    private SwaggerCloudAdminProperties adminProperties;
    @Mock
    private SwaggerCloudClientProperties clientProperties;
    @Mock
    private RestTemplate template;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateHeaders(){

        service.registerApplication();

    }
}
