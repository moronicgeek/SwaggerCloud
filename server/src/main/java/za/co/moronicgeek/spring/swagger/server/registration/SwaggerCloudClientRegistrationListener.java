package za.co.moronicgeek.spring.swagger.server.registration;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

public class SwaggerCloudClientRegistrationListener {
    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerCloudClientRegistrationListener.class);

    private DiscoveryClient discoveryClient;

    private long registerPeriod = 10_000L;
    private volatile ScheduledFuture<?> scheduledTask ;
    private final TaskScheduler taskScheduler = new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());

    public SwaggerCloudClientRegistrationListener( DiscoveryClient discoveryClient){
        this.discoveryClient = discoveryClient;

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

        for(Application appl : discoveryClient.getApplications().getRegisteredApplications()){
            LOGGER.info(appl.getName());

        }

                    }catch(Exception e){
                        LOGGER.warn("The server is still not up we will keep trying.");
                    }
                }
            }, registerPeriod);


        }

    }



