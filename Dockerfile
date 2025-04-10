FROM openjdk:21
EXPOSE 8080
ADD target/devop-automation.jar devop-automation.jar
ENTRYPOINT ["java", "-jar", "devop-automation.jar"]