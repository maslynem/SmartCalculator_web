FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} /app/src/app.jar
ENTRYPOINT ["java","-jar","/app/src/app.jar"]