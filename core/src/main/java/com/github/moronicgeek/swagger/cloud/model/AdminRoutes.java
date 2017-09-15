package com.github.moronicgeek.swagger.cloud.model;

/**
 * Created by muhammedpatel on 2016/08/08.
 */
public enum AdminRoutes {


    REGISTER("/register"),
    DEREGISTER("/unregister"),
    CONTEXT("/swaggercloud"),
    PROPERTIES("/swaggercloud/properties");


    private final String path;

    AdminRoutes(final String path) {
        this.path =  path;
    }

    public String getPath() {
        return path;
    }


}
