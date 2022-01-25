FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/scala-2.13/server-assembly-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
