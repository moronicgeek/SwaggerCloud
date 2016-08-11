package za.co.moronicgeek.spring.swagger.server.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import za.co.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
//TODO probably a good idea to create an interface for other stores like hazel cast and the myriad other stores. One Day!!!
public class Registry {
    private static Logger LOGGER = LoggerFactory.getLogger(Registry.class);
    private ConcurrentHashMap<String, Set<ApplicationRegistrationMetadata>> registry = new ConcurrentHashMap<>();

    public boolean addApi(ApplicationRegistrationMetadata metadata) {
        Assert.notNull(metadata);
        Assert.notNull(metadata.getGroupId());
        Assert.notNull(metadata.getSwaggerUrl());

        Set<ApplicationRegistrationMetadata> swaggerGroup = registry.get(metadata.getGroupId());
        if (swaggerGroup == null) {
            Set<ApplicationRegistrationMetadata> set = new HashSet<ApplicationRegistrationMetadata>();
            set.add(metadata);
            registry.put(metadata.getGroupId(), set);

        } else {
            swaggerGroup.add(metadata);
        }
        LOGGER.info("Added Api to Registry {}", metadata);
        return true;
    }

    public List<ApplicationRegistrationMetadata> getAllBeans() {
        Enumeration enumeration = registry.elements();
        return Collections.list(enumeration);
    }


    public int size() {
        return registry.size();
    }

    public int sizeOf(String groupId) {
        Set<ApplicationRegistrationMetadata> swaggerGroup = registry.get(groupId);
        if (swaggerGroup != null) {
            return swaggerGroup.size();
        } else {
            return 0;
        }

    }

    public ApplicationRegistrationMetadata getMetadataByGroupId(String groupId) {

        Set<ApplicationRegistrationMetadata> swaggerGroup = registry.get(groupId);
        if (swaggerGroup != null) {

            return swaggerGroup.iterator().next();
        }
        return null;
    }

    public boolean unRegisterApplication(ApplicationRegistrationMetadata metadata) {

        Set<ApplicationRegistrationMetadata> swaggerGroup = registry.get(metadata.getGroupId());

        if (swaggerGroup != null) {
            swaggerGroup.remove(metadata);
            if (swaggerGroup.size() == 0) {
                registry.remove(metadata.getGroupId());
            }
        }
        LOGGER.info("Application deregistration complete {}", metadata);
        return true;
    }


}
