
# Cineplex
Cineplex: A all in one Movie management solution.

##  Versions
- Java 17
- Spring Boot 3.1.2

## Local Environment Setup

- Git: https://git-scm.com/downloads
- Java 17 JDK: https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
- PostgreSQL v15.2: https://www.postgresql.org/download/
- Docker version 20.10.2: https://www.docker.com/products/docker-desktop/

## Environment Variables

Environment variables are in application.properties.

DO NOT include the file in the source control while using sensitive data. For demo project purpose we have included them here as they are not sensitive. BUT in Real never put them in source control.

Below are the default values or default format for some of the environment variables in local environment.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres  
spring.datasource.username=postgres  
spring.datasource.password=asdf
```


## Setup and run project

Download and Install Java 17 JDK.
```
https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
```

Download and Install Docker Desktop
```
https://www.docker.com/products/docker-desktop/
```
Clone the project
```
git clone https://github.com/arfin97/cineplex-bd.git
```
You can now open the project in your desired IDE (Eg. Intillij) and use the UI to build and dockerize the application.

Or you can follow the below steps to use terminal commads to run the project.

Build Jar
```bash
./mvnw clean install
```

Seed the create images and containers
```bash
docker-compose up
```
This will automatically start the server.

# APIs
Go to https://localhost:8080/swagger-ui/index.html/ to see the swagger ui.

Or import this postman collection https://gist.github.com/arfin97/f81a6adf3072c15def056b4e79470d74

