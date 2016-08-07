#Swagger Cloud Admin

![alt text](https://travis-ci.org/moronicgeek/SwaggerCloud.svg?branch=master "Travis CI Build Information")



This is a server/client spring boot application. It allows you to quickly implement a document portal in swagger. 
You may have many instances of your api and register them to the server however only one api will show on the front end as this is meant to be used for your developer environment. 
It's helpful to have a single source of api documentation. As long as the application has a hosted swagger json file it can be registered on the swagger cloud server.
This project has no affliation with the spring cloud or swagger projects.

 
 
##Usage
 
###Client

Simply add the following maven dependency to your project.
```
 <dependency>
            <groupId>za.co.moronicgeek.spring.swagger</groupId>
            <artifactId>client</artifactId>
            <version>${project.version}</version>
  </dependency>
```
 
 
and add the following resouces to your application.properties or application.yml
```
swagger.cloud.boot.admin.url=http://localhost:8084
swagger.cloud.boot.admin.apiPath=/test/register
swagger.cloud.boot.client.name=IchiServerAPI
swagger.cloud.boot.client.swagger-url=http://localhost:8081/swagger.json
```
 
###Server
 The server requires a basic spring boot application with the (@EnableSwaggerCloudServer) annotation in your application config.
 
 ```
   <dependency>
             <groupId>za.co.moronicgeek.spring.swagger</groupId>
             <artifactId>server</artifactId>
             <version>1.0-SNAPSHOT</version>
   </dependency>
 ```
 
 add this annotation to your SpringBootApplication configuration
 
 ```
 @EnableSwaggerCloud
 ```
 
 

###Gradle

 TODO
 
