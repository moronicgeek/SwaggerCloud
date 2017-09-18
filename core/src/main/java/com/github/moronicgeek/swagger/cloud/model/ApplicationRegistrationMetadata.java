package com.github.moronicgeek.swagger.cloud.model;

import java.util.Objects;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
public class ApplicationRegistrationMetadata {


    private int id;
    private String name;
    private String swaggerUrl;
    private String groupId;
    private int port;
    private String host;

    public ApplicationRegistrationMetadata(String name, String swaggerUrl, String groupId, int port, String host) {
        this.name = name;
        this.swaggerUrl = swaggerUrl;
        this.groupId = groupId;
        this.port = port;
        this.host = host;
    }

    public ApplicationRegistrationMetadata() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwaggerUrl() {
        return swaggerUrl;
    }

    public void setSwaggerUrl(String swaggerUrl) {
        this.swaggerUrl = swaggerUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationRegistrationMetadata metadata = (ApplicationRegistrationMetadata) o;
        return id == metadata.id &&
                Objects.equals(name, metadata.name) &&
                Objects.equals(swaggerUrl, metadata.swaggerUrl) &&
                Objects.equals(groupId, metadata.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, swaggerUrl, groupId);
    }

    @Override
    public String toString() {
        return "ApplicationRegistrationMetadata{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", swaggerUrl='" + swaggerUrl + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }


}
