package za.co.discovery.spring.swagger.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.discovery.spring.swagger.client.ApplicationRegistrationMetadata;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@ResponseBody
public class SwaggerRegistrationController {


    private Registry registry;
    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerRegistrationController.class);



    /**
     * Register an application within this admin application.
     *
     * @param app The application infos.
     * @return The registered application.
     */
    @RequestMapping(value = "/api/applications", method = RequestMethod.POST)
    public ResponseEntity<ApplicationRegistrationMetadata> register(@RequestBody ApplicationRegistrationMetadata app) {
        LOGGER.debug("Register application {}", app.toString());
        ApplicationRegistrationMetadata registeredApp = registry.getRegistry().put(app.getName(),app);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredApp);
    }
}
