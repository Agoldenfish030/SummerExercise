FROM maven:3.9.16-eclipse-temurin-17 AS build
COPY backend/src /home/app/src
COPY backend/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM amazoncorretto:17-jdk
COPY --from=build /home/app/target/backend-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/demo.jar"]