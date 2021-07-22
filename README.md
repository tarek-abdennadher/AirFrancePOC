## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 

First, you can execute the `main` method in the `fr.airfrance.poc.PocApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

This application is running on port=9090

## Display in-memory database client

This project is running with an in-memory database H2. It is configured as a file based database to be able to keep data after each build.

To open its' browser based console application you have to open it on http://localhost:9090/h2-console/login.

The settings shown below are needed to be configured

<b>Driver Class:</b> org.h2.Driver

<b>JDBC URL:</b> jdbc:h2:file:./src/main/resources/database/airFrancePocDb

<b>User Name:</b> admin

<b>Password:</b> admin
