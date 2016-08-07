package za.co.moronicgeek.spring.swagger.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Created by muhammedpatel on 2016/08/07.
 */
public class RegistrationApplicationListener {

    private static Logger LOGGER = LoggerFactory.getLogger(RegistrationApplicationListener.class);
    private final ApplicationRegistrationBean register;

    public RegistrationApplicationListener(ApplicationRegistrationBean register){
        this.register = register;

    }

    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void onApplicationReady(ApplicationReadyEvent event){
        register.registerApplication();
    }


    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void onApplicationShutdown(ContextClosedEvent event){
        register.deregisterApplication();
    }


}
