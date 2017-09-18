package com.github.moronicgeek.swagger.cloud.client.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.moronicgeek.swagger.cloud.model.SwaggerCloudClientProperties;

@RestController
public class SwaggerCloudClientResource {

    @Autowired
    private SwaggerCloudClientProperties properties;


    @RequestMapping(value = "/swaggercloud/properties", method = RequestMethod.GET)
    public SwaggerCloudClientProperties getProperties(){

        return properties;
    }
}
