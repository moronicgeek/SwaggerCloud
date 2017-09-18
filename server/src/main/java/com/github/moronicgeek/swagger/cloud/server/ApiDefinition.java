package com.github.moronicgeek.swagger.cloud.server;

/**
 * Created by muhammedpatel on 2016/08/12.
 */
public class ApiDefinition {


    private String name;
    private String swaggerUrl;
    private String groupId;
    private String description;
    private String host;
    private int port;

    public ApiDefinition() {
    }

    public ApiDefinition(String name, String swaggerUrl, String groupId, String description, String host, int port) {
        this.name = name;
        this.swaggerUrl = swaggerUrl;
        this.groupId = groupId;
        this.description = description;
        this.host = host;
        this.port = port;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiDefinition that = (ApiDefinition) o;

        if (port != that.port) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return host != null ? host.equals(that.host) : that.host == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        return result;
    }
}
