package za.co.moronicgeek.spring.swagger.server;

import za.co.moronicgeek.spring.swagger.client.ApplicationRegistrationMetadata;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
public class Registry {
//TODO need to control the registry a litle. Some validation of the objects and some return data.

//TODO for now this will work.


    private ConcurrentHashMap<String, ApplicationRegistrationMetadata> registry = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, ApplicationRegistrationMetadata> getRegistry() {
        return registry;
    }



}
