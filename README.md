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
http://localhost:<port>/swagger-ui.html

