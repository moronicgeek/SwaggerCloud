package za.co.moronicgeek.spring.swagger.server;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import za.co.moronicgeek.spring.swagger.server.registry.Registry;
import za.co.moronicgeek.swagger.cloud.model.ApplicationRegistrationMetadata;

/**
 * Created by muhammedpatel on 2016/08/08.
 */
public class RegistryTest {


    private Registry registry = new Registry();


    public void initiliase() {
        ApplicationRegistrationMetadata metadata = new ApplicationRegistrationMetadata();
        metadata.setId(1);
        metadata.setName("API1");
        metadata.setGroupId("za.co.moronicgeek.api1");
        metadata.setSwaggerUrl("Dont care");

        registry.addApi(metadata);

        ApplicationRegistrationMetadata metadata2 = new ApplicationRegistrationMetadata();
        metadata2.setId(2);
        metadata2.setName("API2");
        metadata2.setGroupId("za.co.moronicgeek.api2");
        metadata2.setSwaggerUrl("Dont care");

        registry.addApi(metadata2);


        ApplicationRegistrationMetadata metadata3 = new ApplicationRegistrationMetadata();
        metadata3.setId(3);
        metadata3.setName("API3");
        metadata3.setGroupId("za.co.moronicgeek.api3");
        metadata3.setSwaggerUrl("Dont care");

        registry.addApi(metadata3);

        ApplicationRegistrationMetadata metadata4 = new ApplicationRegistrationMetadata();
        metadata4.setId(4);
        metadata4.setName("API4");
        metadata4.setGroupId("za.co.moronicgeek.api4");
        metadata4.setSwaggerUrl("Dont care");

        registry.addApi(metadata4);

    }

    @Test
    public void removeFromRegistry(){
        initiliase();
        ApplicationRegistrationMetadata metadata4 = new ApplicationRegistrationMetadata();
        metadata4.setId(4);
        metadata4.setName("API4");
        metadata4.setGroupId("za.co.moronicgeek.api4");
        metadata4.setSwaggerUrl("Dont care");

        registry.unRegisterApplication(metadata4);


        Assert.assertNull(registry.getMetadataByGroupId("za.co.moronicgeek.api4"));


    }

    @Test
    public void testAddToRegistry() {

        ApplicationRegistrationMetadata metadata = new ApplicationRegistrationMetadata();
        metadata.setId(1);
        metadata.setName("APIName");
        metadata.setSwaggerUrl("I am a url");
        metadata.setGroupId("za.co.moronicgeek");

        registry.addApi(metadata);

        Assert.assertThat(registry.size(), Is.is(1));

    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddToRegistryException() {

        ApplicationRegistrationMetadata metadata = new ApplicationRegistrationMetadata();
        metadata.setId(1);
        metadata.setName("APIName");
        metadata.setSwaggerUrl(null);
        metadata.setGroupId("za.co.moronicgeek");

        registry.addApi(metadata);

        Assert.assertThat(registry.size(), Is.is(1));

    }

    @Test
    public void testSize() {
        initiliase();
        Assert.assertThat(registry.size(), Is.is(4));
    }

    @Test
    public void testAddToExistingGroup() {
        initiliase();
        ApplicationRegistrationMetadata metadata4 = new ApplicationRegistrationMetadata();
        metadata4.setId(4);
        metadata4.setName("API4");
        metadata4.setGroupId("za.co.moronicgeek.api4");
        metadata4.setSwaggerUrl("Another");
        registry.addApi(metadata4);
        Assert.assertThat(registry.sizeOf("za.co.moronicgeek.api4"), Is.is(2));
        ApiDefinition meta = registry.getMetadataByGroupId("za.co.moronicgeek.api4");

        ApplicationRegistrationMetadata metadata2 = new ApplicationRegistrationMetadata();
        metadata2.setId(4);
        metadata2.setName("API4");
        metadata2.setGroupId("za.co.moronicgeek.api4");
        metadata2.setSwaggerUrl("Dont care");
        registry.unRegisterApplication(metadata2);
        Assert.assertThat(registry.sizeOf("za.co.moronicgeek.api4"), Is.is(1));

    }


}
