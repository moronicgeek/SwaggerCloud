package za.co.moronicgeek.spring.swagger.client.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import za.co.moronicgeek.spring.swagger.client.ApplicationRegistrationService;

/**
 * Created by muhammedpatel on 2016/08/07.
 */
public class RegistrationApplicationListener {

    private static Logger LOGGER = LoggerFactory.getLogger(RegistrationApplicationListener.class);
    private final ApplicationRegistrationService register;

    public RegistrationApplicationListener(ApplicationRegistrationService register) {
        this.register = register;

    }

    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void onApplicationReady(ApplicationReadyEvent event) {
        LOGGER.info("Attempting to Register Application with Swagger Cloud Server");
        try {
            register.registerApplication();
        } catch (Exception e) {

            LOGGER.warn("Let's be real quiet like about this..... SWAGGER CLOUD IS DOWN!!",e);
        }

    }


    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void onApplicationShutdown(ContextClosedEvent event) {
        try {
            register.deregisterApplication();
        } catch (Exception ex) {
            LOGGER.warn("Server is probably down!! Not worry you are dead so you don't care do you?");
        }
    }


}
