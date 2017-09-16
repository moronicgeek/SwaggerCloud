package com.github.moronicgeek.swagger.cloud.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by muhammedpatel on 2016/08/01.
 */
@ConfigurationProperties("spring.swagger.cloud")
public class SwaggerCloudProperties {

    private String server = "localhost";
    private int port = 8080;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
