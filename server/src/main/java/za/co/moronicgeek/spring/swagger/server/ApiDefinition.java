package za.co.moronicgeek.spring.swagger.server;

/**
 * Created by muhammedpatel on 2016/08/12.
 */
public class ApiDefinition {


    private String name;
    private String swaggerUrl;
    private String groupId;
    private String description;


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
}
