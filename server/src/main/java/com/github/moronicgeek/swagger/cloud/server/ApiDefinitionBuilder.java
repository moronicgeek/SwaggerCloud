package com.github.moronicgeek.swagger.cloud.server;

public class ApiDefinitionBuilder {
    private String name;
    private String swaggerUrl;
    private String groupId;
    private String description;

    public ApiDefinitionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ApiDefinitionBuilder setSwaggerUrl(String swaggerUrl) {
        this.swaggerUrl = swaggerUrl;
        return this;
    }

    public ApiDefinitionBuilder setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public ApiDefinitionBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ApiDefinition createApiDefinition() {
        return new ApiDefinition(name, swaggerUrl, groupId, description);
    }
}