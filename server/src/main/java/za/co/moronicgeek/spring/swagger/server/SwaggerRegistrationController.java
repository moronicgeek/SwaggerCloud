package za.co.moronicgeek.spring.swagger.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import za.co.moronicgeek.swagger.cloud.ApplicationRegistrationMetadata;

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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ApplicationRegistrationMetadata> register(@RequestBody ApplicationRegistrationMetadata app) {
        LOGGER.debug("Register application {}", app.toString());
        ApplicationRegistrationMetadata registeredApp = registry.getRegistry().put(app.getName(),app);
        return status(HttpStatus.CREATED).body(registeredApp);
    }

    /**
     * Register an application within this admin application.
     *

     * @return The registered application.
     */
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
    @RequestMapping(value = "/api/size", method = RequestMethod.GET)
    public ResponseEntity<String> size() {
        LOGGER.debug("It's all a test " );

        return ResponseEntity.ok(registry.getRegistry().size()+"");
    }
}
