package com.github.moronicgeek.swagger.cloud.server.registry;

import com.github.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;
import com.github.moronicgeek.swagger.cloud.server.ApiDefinition;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
//TODO probably a good idea to create an interface for other stores like hazel cast and the myriad other stores. One Day!!!
public class Registry {
    private static Logger LOGGER = LoggerFactory.getLogger(Registry.class);
    private ConcurrentHashMap<String, Set<ApiDefinition>> registry = new ConcurrentHashMap<>();

    public boolean addApi(ApplicationRegistrationMetadata metadata) {
        Assert.notNull(metadata);
        Assert.notNull(metadata.getGroupId());
        Assert.notNull(metadata.getSwaggerUrl());
        ApiDefinition api = new ApiDefinition();
        try {
            Swagger swagger = new SwaggerParser().read(metadata.getSwaggerUrl());
            if (swagger != null) {
                api.setDescription(swagger.getInfo().getDescription());
            }
        }catch (HttpClientErrorException exception){

            return true;
        }

            api.setGroupId(metadata.getGroupId());
            api.setSwaggerUrl(metadata.getSwaggerUrl());
            api.setName(metadata.getName());
            api.setHost(metadata.getHost());
            api.setPort(metadata.getPort());


        Set<ApiDefinition> swaggerGroup = registry.get(metadata.getGroupId());
        if (swaggerGroup == null) {
            Set<ApiDefinition> set = new HashSet<ApiDefinition>();
            set.add(api);
            registry.put(metadata.getGroupId(), set);

        } else {
            swaggerGroup.add(api);
        }
        LOGGER.info("Added Api to Registry {}", metadata);
        return true;
    }

    public List<ApiDefinition> getAllBeans() {
        Enumeration enumeration = registry.elements();
        return Collections.list(enumeration);
    }


    /**
     *
     * @return the size of groups not the size of the registry
     */
    public int size() {
        return registry.size();
    }

    public int sizeOf(String groupId) {
        Set<ApiDefinition> swaggerGroup = registry.get(groupId);
        if (swaggerGroup != null) {
            return swaggerGroup.size();
        } else {
            return 0;
        }

    }

    public ApiDefinition getMetadataByGroupId(String groupId) {
        Set<ApiDefinition> swaggerGroup = registry.get(groupId);
        if (swaggerGroup != null) {

            return swaggerGroup.iterator().next();
        }
        return null;
    }

    public boolean unRegisterApplication(ApplicationRegistrationMetadata metadata) {
        Set<ApiDefinition> swaggerGroup = registry.get(metadata.getGroupId());
        Set<ApiDefinition> temp = new HashSet<>();

        if (swaggerGroup != null) {
            temp.addAll(swaggerGroup);
            Iterator<ApiDefinition> enumeration = temp.iterator();

            while (enumeration.hasNext()) {
                ApiDefinition def = enumeration.next();
                if (def.getGroupId().equals(metadata.getGroupId()) && def.getSwaggerUrl().equals(metadata.getSwaggerUrl())) {
                    swaggerGroup.remove(def);
                }
            }
            if (swaggerGroup.size() == 0) {
                registry.remove(metadata.getGroupId());
            }
        }
        LOGGER.info("Application deregistration complete {}", metadata);
        return true;
    }

    public void purge(){
        registry = new ConcurrentHashMap<>();

    }

    public ConcurrentHashMap<String, Set<ApiDefinition>> getRegistry() {
        return registry;
    }
}
