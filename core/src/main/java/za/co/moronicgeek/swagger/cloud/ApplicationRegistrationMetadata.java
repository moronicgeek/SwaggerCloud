package za.co.moronicgeek.swagger.cloud;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by muhammedpatel on 2016/08/06.
 */
public class ApplicationRegistrationMetadata {


    private int id;
    private String name;
    private String swaggerUrl;

    public ApplicationRegistrationMetadata(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.swaggerUrl = builder.swaggerUrl;
    }

    @JsonCreator
    public static ApplicationRegistrationMetadata fromJson(@JsonProperty("id") int id,
                                       @JsonProperty("name") String name,
                                       @JsonProperty("swaggerUrl") String swaggerUrl){

        Builder builder = create(name).withId(id).withName(name).withSwaggerUrl(swaggerUrl);

        return builder.build();
    }


    public static Builder create(ApplicationRegistrationMetadata bean){
        return new Builder(bean);
    }
    public static Builder create(String name){
        return new Builder(name);
    }

    public static class Builder {
        private int id;
        private String name;
        private String swaggerUrl;

        private Builder(ApplicationRegistrationMetadata bean){
            this.id  = bean.getId();
            this.name = bean.getName();
            this.swaggerUrl = bean.getSwaggerUrl();
        }

        private Builder(String name){

            this.name = name;
        }


       public Builder withId(int id){
           this.id = id;
           return this;
       }
       public Builder withName(String name){
           this.name = name;
           return this;
       }

       public Builder withSwaggerUrl(String swaggerUrl){
           this.swaggerUrl = swaggerUrl;
           return this;
       }

        public ApplicationRegistrationMetadata build() {
            return new ApplicationRegistrationMetadata(this);
        }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationRegistrationMetadata that = (ApplicationRegistrationMetadata) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(swaggerUrl, that.swaggerUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, swaggerUrl);
    }

    @Override
    public String toString() {
        return "ApplicationRegistrationMetadata{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", swaggerUrl='" + swaggerUrl + '\'' +
                '}';
    }
}
