package za.co.moronicgeek.spring.swagger.server.registration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import za.co.moronicgeek.spring.swagger.server.registry.Registry;
import za.co.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;
import za.co.moronicgeek.swagger.cloud.model.SwaggerCloudClientProperties;
import za.co.moronicgeek.swagger.cloud.rest.SwaggerCloudClientRestTemplate;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

public class SwaggerCloudClientRegistrationListener {
    private static Logger LOGGER = LoggerFactory.getLogger(SwaggerCloudClientRegistrationListener.class);
    private final TaskScheduler taskScheduler = new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
    @Autowired(required = false)
    private DiscoveryClient discoveryClient;
    private long registerPeriod = 10_000L;
    private volatile ScheduledFuture<?> scheduledTask;

    @Autowired
    private Registry registry;

    @Autowired
    private SwaggerCloudClientRestTemplate template;


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

                            List<ServiceInstance> instances = discoveryClient.getInstances(appl);
                            for (ServiceInstance instance : instances) {
                                ApplicationRegistrationMetadata metadata = new ApplicationRegistrationMetadata();
                                LOGGER.debug(instance.getHost());
                                LOGGER.debug(instance.getServiceId());
                                LOGGER.debug(instance.getMetadata().toString());
                                LOGGER.debug(instance.getPort() + "");
                                LOGGER.debug(instance.getUri().toString());

                                LOGGER.debug(instance.getUri().toString());
                                SwaggerCloudClientProperties props = template.getClientProperties(instance.getUri().toString());
                                metadata.setGroupId(props.getGroupId());
                                metadata.setSwaggerUrl(props.getSwaggerUrl() );
                                registry.addApi(metadata);
                                LOGGER.debug(props.toString());


                            }


                        }

                } catch (Exception e) {
                    LOGGER.warn("Server not swagger cloud enabled. Cannot find client endpoint enabled by swagger cloud client library", e);
                }
            }
        }, registerPeriod);


    }

}



