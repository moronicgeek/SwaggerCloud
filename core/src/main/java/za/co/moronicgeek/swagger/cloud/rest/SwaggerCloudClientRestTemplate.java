package za.co.moronicgeek.swagger.cloud.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import za.co.moronicgeek.swagger.cloud.model.SwaggerCloudClientProperties;

@Component
public class SwaggerCloudClientRestTemplate {


    private RestTemplate restTemplate;

    private String resourceUrl;



    public SwaggerCloudClientRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public SwaggerCloudClientProperties getClientProperties(String resourceUrl) {
        return restTemplate.getForEntity(resourceUrl + "/swaggercloud/properties", SwaggerCloudClientProperties.class).getBody();
    }

}
