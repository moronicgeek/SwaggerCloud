package za.co.moronicgeek.spring.swagger.server.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.moronicgeek.spring.swagger.server.registry.Registry;
import za.co.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@ResponseBody
public class SwaggerRegistrationController {


    private Registry registry = new Registry();
    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerRegistrationController.class);



    /**
     * Register an application within this admin application.
     *
     * @param app The application infos.
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ApplicationRegistrationMetadata> register(@RequestBody ApplicationRegistrationMetadata app) {
        LOGGER.debug("Register application {}", app.toString());
        registry.addApi(app);
        return status(HttpStatus.CREATED).body(app);
    }



    /**
     * UnRegister an application within this admin application.
     *
     * @param app The application infos.
     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public ResponseEntity<ApplicationRegistrationMetadata> unregister(@RequestBody ApplicationRegistrationMetadata app) {
        LOGGER.debug("Register application {}", app.toString());
        registry.unRegisterApplication(app);
        return status(HttpStatus.OK).body(app);
    }

    /**
     * Register an application within this admin application.
     *

     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public ResponseEntity<String> register() {
        LOGGER.debug("It's all a test " );

        return ResponseEntity.ok("Hello From the server");
    }

    /**
     * Register an application within this admin application.
     *

     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public ResponseEntity<String> size() {
        LOGGER.debug("It's all a test " );

        return ResponseEntity.ok(registry.size()+"");
    }


    /**
     * Register an application within this admin application.
     *

     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/sizeof/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<String> size(@PathVariable String groupId) {
        LOGGER.debug("It's all a test " );

        return ResponseEntity.ok(registry.sizeOf(groupId)+"");
    }

    /**
     * Register an application within this admin application.
     *

     * @return The registered application.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<ApplicationRegistrationMetadata>> retrieveRegsiteredApis() {
        LOGGER.debug("Retrieving all registered application" );
        return ResponseEntity.ok(registry.getAllBeans());
    }
}
