FROM openjdk:8-jre
COPY target/StudyBoard-*.jar app-studyboard.jar
CMD ["java", "-jar", "app-studyboard.jar"]