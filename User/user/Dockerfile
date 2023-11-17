FROM maven:3.8.3-openjdk-17
ARG JAR_FILE=target/*.jar
COPY ./target/user-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]