FROM openjdk:21
EXPOSE 8080
COPY target/devOp-integration.jar devOp-integration.jar
ENTRYPOINT ["java", "-jar", "devOp-integration.jar"]