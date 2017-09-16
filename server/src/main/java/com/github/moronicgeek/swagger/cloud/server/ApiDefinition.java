package com.github.moronicgeek.swagger.cloud.server;

/**
 * Created by muhammedpatel on 2016/08/12.
 */
public class ApiDefinition {


    private String name;
    private String swaggerUrl;
    private String groupId;
    private String description;

    public ApiDefinition(String name, String swaggerUrl, String groupId, String description) {
        this.name = name;
        this.swaggerUrl = swaggerUrl;
        this.groupId = groupId;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiDefinition that = (ApiDefinition) o;

        if (swaggerUrl != null ? !swaggerUrl.equals(that.swaggerUrl) : that.swaggerUrl != null) return false;
        return groupId != null ? groupId.equals(that.groupId) : that.groupId == null;
    }

    @Override
    public int hashCode() {
        int result = swaggerUrl != null ? swaggerUrl.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        return result;
    }
}
