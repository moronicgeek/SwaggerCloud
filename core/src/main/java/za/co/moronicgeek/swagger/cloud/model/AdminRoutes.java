package za.co.moronicgeek.swagger.cloud.model;

/**
 * Created by muhammedpatel on 2016/08/08.
 */
public enum AdminRoutes {
    REGISTER("/test/register/"),DEREGISTER("/test/unregister/");


    private String path;
    AdminRoutes(final String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
