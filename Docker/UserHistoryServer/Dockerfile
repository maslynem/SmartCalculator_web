FROM openjdk:11-jdk-alpine
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} /app/src/app.jar
ENTRYPOINT ["java","-jar","/app/src/app.jar"]