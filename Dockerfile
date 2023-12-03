FROM openjdk:17-jdk
COPY build/libs/kpaas-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]