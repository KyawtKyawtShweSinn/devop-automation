FROM openjdk:21
EXPOSE 8080
ADD target/devOp-integration.jar devOp-integration.jar
ENTRYPOINT ["java", "-jar", "devOp-integration.jar"]