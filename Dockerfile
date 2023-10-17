FROM amazoncorretto:17-alpine-jdk
COPY ./target/cineplex-bd.jar cineplex-bd.jar
ENTRYPOINT ["java","-jar","cineplex-bd.jar"]
