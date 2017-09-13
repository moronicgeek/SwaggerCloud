package za.co.moronicgeek.spring.swagger.server.registration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

public class SwaggerCloudClientRegistrationListener {
    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerCloudClientRegistrationListener.class);
    private final TaskScheduler taskScheduler = new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
    @Autowired(required = false)
    private DiscoveryClient discoveryClient;
    private long registerPeriod = 10_000L;
    private volatile ScheduledFuture<?> scheduledTask;

    public SwaggerCloudClientRegistrationListener() {


    }

    public void stopRegisterTask() {
        if (scheduledTask != null && !scheduledTask.isDone()) {
            scheduledTask.cancel(true);
            LOGGER.debug("Canceled registration task");
        }
    }

    @EventListener
    @Order(Ordered.LOWEST_PRECEDENCE)
    public void onApplicationReady(ApplicationReadyEvent event) {
        LOGGER.info("Attempting to Register Application with Swagger Cloud Server");


        if (scheduledTask != null && !scheduledTask.isDone()) {
            return;
        }

        scheduledTask = taskScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    if (discoveryClient != null)
                        for (String appl : discoveryClient.getServices()) {
                            LOGGER.info(appl);


                        }

                } catch (Exception e) {
                    LOGGER.warn("The server is still not up we will keep trying.");
                }
            }
        }, registerPeriod);


    }

}



