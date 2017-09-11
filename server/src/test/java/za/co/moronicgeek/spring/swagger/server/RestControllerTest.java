package za.co.moronicgeek.spring.swagger.server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.co.moronicgeek.spring.swagger.server.registry.Registry;
import za.co.moronicgeek.spring.swagger.server.resource.SwaggerRegistrationController;
import za.co.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by muhammedpatel on 2016/08/13.
 */
public class RestControllerTest {


    private static final String APPLICATION_TEST_JSON = "{ \"name\":\"test\", \"healthUrl\":\"http://localhost/mgmt/health\"}";
    private static final String APPLICATION_TWICE_JSON = "{ \"name\":\"twice\", \"healthUrl\":\"http://localhost/mgmt/health\"}";
    private MockMvc mvc;

    @Mock
    Registry registry;

    @InjectMocks
    SwaggerRegistrationController controller;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void testRegister() throws Exception{

        Mockito.when(registry.addApi(Matchers.any(ApplicationRegistrationMetadata.class))).thenReturn(true);
        MvcResult result = mvc
                .perform(post("/register").contentType(MediaType.APPLICATION_JSON)
                        .content(APPLICATION_TEST_JSON))
                .andExpect(status().isCreated()).andReturn();

    }


    @Test
    public void testDeRegister1() throws Exception{

        Mockito.when(registry.unRegisterApplication(Matchers.any(ApplicationRegistrationMetadata.class))).thenReturn(true);
        MvcResult result = mvc
                .perform(post("/unregister").contentType(MediaType.APPLICATION_JSON)
                        .content(APPLICATION_TEST_JSON))
                .andExpect(status().isOk()).andReturn();

    }


    @Test
    public void testSize() throws Exception{

        Mockito.when(registry.size()).thenReturn(1);

        MvcResult result = mvc
                .perform(get("/size").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.size").value("1")).andReturn();

    }

}
