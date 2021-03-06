package com.github.moronicgeek.swagger.cloud.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.github.moronicgeek.swagger.cloud.model.AdminRoutes;
import com.github.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;
import com.github.moronicgeek.swagger.cloud.model.SwaggerCloudAdminProperties;
import com.github.moronicgeek.swagger.cloud.model.SwaggerCloudClientProperties;

import java.util.Collections;

/**
 * Created by muhammedpatel on 2016/08/06.
 * <p>
 * This class will register an api with the swager cloud server
 *
 * This service is only used in the instance where eureka does not exist. In the below model the client registers/deregisters itself
 * from the server.
 */
public class ApplicationRegistrationService {

    private static HttpHeaders HTTP_HEADERS = createHttpHeaders();
    private SwaggerCloudAdminProperties adminProperties;
    private SwaggerCloudClientProperties clientProperties;
    private RestTemplate template;

    public ApplicationRegistrationService(SwaggerCloudClientProperties clientProperties, SwaggerCloudAdminProperties adminProperties, RestTemplate template) {
        this.adminProperties = adminProperties;
        this.clientProperties = clientProperties;
        this.template = template;
    }

    private static HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return HttpHeaders.readOnlyHttpHeaders(headers);
    }

    public static HttpHeaders getHttpHeaders() {
        return HTTP_HEADERS;
    }

    public static void setHttpHeaders(HttpHeaders httpHeaders) {
        HTTP_HEADERS = httpHeaders;
    }

    public boolean registerApplication() {
        boolean registrationSuccessful = false;
        ApplicationRegistrationMetadata self = createMetaDataApplication();

        ResponseEntity<Boolean> response = template.postForEntity(adminProperties.getUrl() +AdminRoutes.CONTEXT.getPath()+ AdminRoutes.REGISTER.getPath(),
                new HttpEntity<>(self, HTTP_HEADERS), Boolean.class);
        if (response == null){
            return false;
        }

        return registrationSuccessful;
    }

    public boolean deregisterApplication() {
        boolean deregistered = false;
        ApplicationRegistrationMetadata self = createMetaDataApplication();
        //this will be used some day
        ResponseEntity<Boolean> response = template.postForEntity(adminProperties.getUrl() +AdminRoutes.CONTEXT.getPath()+ AdminRoutes.DEREGISTER.getPath(),
                new HttpEntity<>(self, HTTP_HEADERS), Boolean.class);
        if (response == null){
            return false;
        }
        return deregistered;
    }

    private ApplicationRegistrationMetadata createMetaDataApplication() {
        return new ApplicationRegistrationMetadata(clientProperties.getName(),clientProperties.getSwaggerUrl(),clientProperties.getGroupId(),clientProperties.getServerPort(),clientProperties.getServer().getContextPath());
    }

    public SwaggerCloudAdminProperties getAdminProperties() {
        return adminProperties;
    }

    public void setAdminProperties(SwaggerCloudAdminProperties adminProperties) {
        this.adminProperties = adminProperties;
    }

    public SwaggerCloudClientProperties getClientProperties() {
        return clientProperties;
    }

    public void setClientProperties(SwaggerCloudClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    public RestTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }
}
