package za.co.moronicgeek.sample.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.co.moronicgeek.spring.swagger.server.registry.Registry;

import java.util.List;

/**
 * Created by muhammedpatel on 2016/08/06.
 */

public class SwaggerRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerRegistrationController.class);
    @Autowired
    private Registry registry;
    @Autowired(required = false)
    private DiscoveryClient discoveryClient;

    /**
     * Register an application within this admin application.
     *
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/test/scanme", method = RequestMethod.GET)
    public List<String> scan() {

        LOGGER.debug(" Starting Scan .... ");

        for (String app : discoveryClient.getServices()) {
            LOGGER.debug(app);

        }
        return discoveryClient.getServices();
    }


}
