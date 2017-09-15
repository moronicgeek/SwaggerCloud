package za.co.moronicgeek.swagger.cloud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by muhammedpatel on 2016/08/01.
 */
@ConfigurationProperties("swagger.cloud.boot.client")
public class SwaggerCloudClientProperties {
    private String adminServerUrl = "";
    private String swaggerUrl = "";
    private String name = "";
    private String groupId = "";
    //TODO We should probably derive this from the swagger json file and perhaps some other data. Think !!!
    private String description  ="";

    @Autowired
    @JsonIgnore
    private ServerProperties server;

    private Integer serverPort;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSwaggerUrl() {
        return swaggerUrl;
    }

    public void setSwaggerUrl(String swaggerUrl) {
        this.swaggerUrl = swaggerUrl;
    }


    public String getAdminServerUrl() {
        return adminServerUrl;
    }

    public void setAdminServerUrl(String adminServerUrl) {
        this.adminServerUrl = adminServerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServerProperties getServer() {
        return server;
    }

    public void setServer(ServerProperties server) {
        this.server = server;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public String toString() {
        return "SwaggerCloudClientProperties{" +
                "adminServerUrl='" + adminServerUrl + '\'' +
                ", swaggerUrl='" + swaggerUrl + '\'' +
                ", name='" + name + '\'' +
                ", groupId='" + groupId + '\'' +
                ", description='" + description + '\'' +
                ", server=" + server +
                ", serverPort=" + serverPort +
                '}';
    }
}
