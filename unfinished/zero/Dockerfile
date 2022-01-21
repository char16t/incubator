FROM openjdk:8
COPY  target/scala-2.12/zero-assembly-0.1.jar /opt/zero.jar
WORKDIR /opt
ENTRYPOINT ["java","-jar","zero.jar"]
EXPOSE 8080
