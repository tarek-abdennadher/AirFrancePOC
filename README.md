## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [MangoDB](https://www.mongodb.com/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. First, you have to create a database named "airFrancePoc", then you can execute the `main` method in the `fr.airfrance.poc.PocApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

This application is running on port=9090
