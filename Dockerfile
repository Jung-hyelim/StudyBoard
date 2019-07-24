FROM openjdk:8-jre
COPY target/studyboard-*.jar app-studyboard.jar
CMD ["java", "-jar", "app-studyboard.jar"]