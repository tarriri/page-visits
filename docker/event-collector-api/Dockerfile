FROM openjdk:17
COPY ../../event-collector-api/build/libs/*.jar event-collector-api.jar

ENTRYPOINT ["java", "-jar", "/event-collector-api.jar"]