package za.co.moronicgeek.spring.swagger.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.co.moronicgeek.swagger.cloud.ApplicationRegistrationMetadata;

import java.util.Collections;
import java.util.Map;

/**
 * Created by muhammedpatel on 2016/08/06.
 *
 * This class will register an api with the swager cloud server
 */
public class ApplicationRegistrationBean {

    private SwaggerCloudAdminProperties adminProperties;
    private SwaggerCloudClientProperties clientProperties;
    private final RestTemplate template;


    public ApplicationRegistrationBean(SwaggerCloudClientProperties clientProperties,SwaggerCloudAdminProperties adminProperties,RestTemplate template){
        this.adminProperties = adminProperties;
        this.clientProperties = clientProperties;
        this.template = template;
    }

    private static HttpHeaders HTTP_HEADERS = createHttpHeaders();
    public boolean registerApplication(){
        boolean registrationSuccessful = false;
        ApplicationRegistrationMetadata self = createMetaDataApplication();
        ResponseEntity<Map> response = template.postForEntity(adminProperties.getUrl()+adminProperties.getApiPath(),
                new HttpEntity<>(self, HTTP_HEADERS), Map.class);
        adminProperties.getUrl();


        return registrationSuccessful;
    }

    public boolean deregisterApplication(){

        boolean deregistered = false;
        ApplicationRegistrationMetadata self = createMetaDataApplication();
        ResponseEntity<Map> response = template.postForEntity(adminProperties.getUrl()+adminProperties.getApiPath(),
                new HttpEntity<>(self, HTTP_HEADERS), Map.class);
        adminProperties.getUrl();
        return deregistered;
    }


    private ApplicationRegistrationMetadata createMetaDataApplication() {
        return ApplicationRegistrationMetadata.create(clientProperties.getName()).withSwaggerUrl(clientProperties.getSwaggerUrl()).build();
    }


    private static HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return HttpHeaders.readOnlyHttpHeaders(headers);
    }
}
