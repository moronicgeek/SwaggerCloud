package za.co.moronicgeek.spring.swagger.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by muhammedpatel on 2016/08/01.
 */
@ConfigurationProperties("swagger.cloud.boot.client")
public class SwaggerCloudClientProperties {
    private String adminServerUrl = "http://localhost:4891";
    private String swaggerUrl = "http://localhost:9384/swagger.json";
    private String name = "";

    @Autowired
    private ServerProperties server;

    private Integer serverPort;



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
}
