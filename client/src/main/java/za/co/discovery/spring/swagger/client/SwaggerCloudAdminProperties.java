package za.co.discovery.spring.swagger.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
@ConfigurationProperties("swagger.cloud.boot.admin")
public class SwaggerCloudAdminProperties {
    /**
     * The admin server urls to register at
     */
    private String url;

    /**
     * The admin rest-apis path.
     */
    private String apiPath = "/swaggercloud/register/";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
}
