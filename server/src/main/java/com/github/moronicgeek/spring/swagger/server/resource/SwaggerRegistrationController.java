package com.github.moronicgeek.spring.swagger.server.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.moronicgeek.spring.swagger.server.ApiDefinition;
import com.github.moronicgeek.spring.swagger.server.registry.Registry;
import com.github.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@ResponseBody
public class SwaggerRegistrationController {

    @Autowired
    private Registry registry;

   @Autowired(required = false)
   private DiscoveryClient discoveryClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerRegistrationController.class);


    /**
     * Register an application within this admin application.
     *
     * @param app The application infos.
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody ApplicationRegistrationMetadata app) {
        LOGGER.debug("Register application {}", app.toString());
        registry.addApi(app);
        return status(HttpStatus.CREATED).body(true);
    }


    /**
     * UnRegister an application within this admin application.
     *
     * @param app The application infos.
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public ResponseEntity<Boolean> unregister(@RequestBody ApplicationRegistrationMetadata app) {
        LOGGER.debug("Unregister application {}", app.toString());
        registry.unRegisterApplication(app);
        return status(HttpStatus.OK).body(true);
    }



    /**
     * Register an application within this admin application.
     *
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public ResponseEntity<String> size() {
        LOGGER.debug("It's all a test ");

        return ResponseEntity.ok("{\"size\" : \""+registry.size() +"\"}");
    }


    /**
     * Register an application within this admin application.
     *
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/sizeof/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<String> size(@PathVariable String groupId) {

        LOGGER.debug("Retrieving size of " + registry.sizeOf(groupId) );

        return ResponseEntity.ok(registry.sizeOf(groupId) + "");
    }

    /**
     * Register an application within this admin application.
     *
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<ApiDefinition>> retrieveRegsiteredApis() {
        LOGGER.debug("Retrieving all registered application");
        return ResponseEntity.ok(registry.getAllBeans());
    }



    /**
     * Register an application within this admin application.
     *
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/scan", method = RequestMethod.GET)
    public Boolean scan() {

        LOGGER.debug(" Starting Scan .... ");

        for (String app : discoveryClient.getServices()) {
            LOGGER.debug(app);
        }
        return new Boolean(true);
    }


}
