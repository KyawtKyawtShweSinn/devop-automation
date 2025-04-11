FROM openjdk:21
EXPOSE 8000
ADD target/devop-automation.jar devop-automation.jar
ENTRYPOINT ["java", "-jar", "devop-automation.jar"]