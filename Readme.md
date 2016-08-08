Swagger Cloud Admin
===================
[![Travis CI](https://travis-ci.org/moronicgeek/SwaggerCloud.svg?branch=master)](https://travis-ci.org/moronicgeek/SwaggerCloud.svg?branch=master)
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1c17f07400bc4dbc9e9bc93861d73bb8)](https://www.codacy.com/app/patel-muhammed/SwaggerCloud?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=moronicgeek/SwaggerCloud&amp;utm_campaign=Badge_Grade)


An API document catalog. This project comprises of a server and client that communicate via rest for registration and document presentation. 

##What it is ?
It is a document cloud catalog. It catalogs your swagger applications documentation.  

##What it's not?
It's not an API Manager
 
 
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
 
