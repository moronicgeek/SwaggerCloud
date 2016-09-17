package za.co.moronicgeek.spring.swagger.client.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import za.co.moronicgeek.spring.swagger.client.ApplicationRegistrationService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by muhammedpatel on 2016/08/07.
 */
public class RegistrationApplicationListener {

    private static Logger LOGGER = LoggerFactory.getLogger(RegistrationApplicationListener.class);
    private final ApplicationRegistrationService register;

    private long registerPeriod = 10_000L;
    private volatile ScheduledFuture<?> scheduledTask ;
    private final TaskScheduler taskScheduler = new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());

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

            LOGGER.info("Registering Task to ping server ever 2 mins. I'll stop when i have registered myself");

            if (scheduledTask != null && !scheduledTask.isDone()) {
                return;
            }

            scheduledTask = taskScheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        register.registerApplication();
                        stopRegisterTask();

                    }catch(Exception e){
                        LOGGER.warn("The server is still not up we will keep trying.");
                    }
                }
            }, registerPeriod);


        }

    }


    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void onApplicationShutdown(ContextClosedEvent event) {
        try {
            register.deregisterApplication();

        } catch (Exception ex) {
            LOGGER.warn("Server is probably down!! No worry you are dead so you don't care, do you?",ex);
        }
        stopRegisterTask();
    }

    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void onApplicationShutdown(ContextStoppedEvent event) {
        try {
            register.deregisterApplication();

        } catch (Exception ex) {
            LOGGER.warn("Server is probably down!! No worry you are dead so you don't care, do you?",ex);

        }
        stopRegisterTask();
    }

    public void stopRegisterTask() {
        if (scheduledTask != null && !scheduledTask.isDone()) {
            scheduledTask.cancel(true);
            LOGGER.debug("Canceled registration task");
        }
    }


}
