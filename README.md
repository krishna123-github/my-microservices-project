# my-microservices-project
My First Microservice project

# Component in it
1. Microservices - CustomerMS & StockMS
2. Eueka Server - EurekaRegistryServer
3. API Gateway - to create static IP and external url will communicate to APIGateway first, APIgateway will balance the request

# Swagger Doc implement in it
Added Dependency in CustoemrMS & StockMS
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency> 

Access the Swagger Doc using this URL
http://MS_IP:MS_PORT/swagger-ui.html

# Eureka Server:
1. It is a a registry server (registering all the microservices)
2. Service discovery happens here (MS name is resolved to IP addresses)
3. Load balancing (Based on available  instances, the request is forwarded equally)
4. Reverse proxy server (request from outside to org’s internal server)
5. Service mesh (service-to-service communication)

Application 1 / Project 1:  For the service registry & discovery (you should start this application first, so that the registry will be up and running, waiting for the microservices to get registered)
This will be used to REGISTER your microservices (created using spring boot)

Application 2/ Project 2: This is the actual microservice project (customer ms, stock ms, product ms, helloworld ms etc.) - you will be starting this next, so that this microservice will be registered into the service registry which is running as project1

If the IP address of microservices changes from time to time (think about DHCP - Dynamic Host Configuration Protocol), all such changes are captured and updated in this registry, so that the client need not worry about these IP changes (one of the features of service mesh)

To achieve, we must include “SPRING CLOUD” dependency in our project

Steps:

Create Registry server and run
Create HelloWorldMS and registry and run

The flow:

Client → Eureka → Actual MS instance is located (spring boot application) → Controller → Service → DAO/JPA	 → Database

# Project 1: Registry Service Server
Create a spring boot project with the name “RegistryServer”

Tomcat is a physical server where you can deploy the application
Eureka is a just a library / logical server, that is deployed as a web application inside Tomcat server

**Add the following dependencies:**

spring-boot-starter-web
Eureka server
Spring cloud

**application.properties** → rename to application.yml (not mandatory)
spring:
  application:
    name: RegistryServer

#Tomcat's port number
server:
  port: 4567

#Eureka related configuration
eureka:
  instance:
    hostname: localhost
  client:
    fetch-registry: false
    register-with-eureka: false

O**pen the main class, add the following annotation:**
package com.opentext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
**@EnableEurekaServer**
// Annotation is used to activate Eureka server related configurations
public class RegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryServerApplication.class, args);
		System.out.println("Registry server started successfully....");
	}

}

Now, run this as spring boot application
If everything goes fine, the registry server will be started on port number 4567 you can access the Eureka home page like this:

# Project 2: Actual Microservices
We need to create a spring boot application with a controller with REST API /hello with GET method, that should return “Hello to Microservices” message in the browser and we must register this in Registry server which is running as project1

Create another project with the name “CustomerMS” & "StockMS"

**Include the following dependencies:**

Spring Web
Eureka Discovery Client

**application.yml**
spring:
 application:
   name: CustomerMS
#Tomcat server details
server:
 port: 4568
#Eureka serer details
eureka:
 client:
   service-url:
     defaultZone: http://localhost:4567/eureka/	
     
**application.yml**
spring:
 application:
   name: StockMS
#Tomcat server details
server:
 port: 4569
#Eureka serer details
eureka:
 client:
   service-url:
     defaultZone: http://localhost:4567/eureka/		

**Main class:**
package com.opentext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
**@EnableDiscoveryClient**
// This annotation is for discovering this as microservice in 
// Eureka server
public class HelloWorldMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldMsApplication.class, args);
		System.out.println("Hello World Microservice started successfully");
	}
}

Save the project
Run this as spring boot app
If everything goes fine, this will be registered as microservice in Eureka server
You can reload the Eureka dashboard and view the miccroservice name

If you have noted, Eureka forms the microservices url like this:

LAPTOP-3H53IF7F:HelloWorldMS:4568
Each registered instance must have a unique identifier in the registry
Using hostname + app name + port ensures the uniqueness
This URL is auto-generated by the spring cloud project

This is how Eureka uniquely identifies a service instance
Format:
**<hostname>:<application-name>:<port-no)**



