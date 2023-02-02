FROM openjdk:17
COPY ../../event-stats-processor/build/libs/*.jar event-stats-processor.jar

ENTRYPOINT ["java", "-jar", "/event-stats-processor.jar"]