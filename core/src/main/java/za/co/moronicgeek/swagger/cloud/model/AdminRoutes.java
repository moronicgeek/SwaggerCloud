package za.co.moronicgeek.swagger.cloud.model;

/**
 * Created by muhammedpatel on 2016/08/08.
 */
public enum AdminRoutes {


    REGISTER("/register"), DEREGISTER("/unregister");

    private String context = "/test";

    private String path;

    AdminRoutes(final String path) {
        this.path = context + path;
    }

    public String getPath() {
        return path;
    }
}
