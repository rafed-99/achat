FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/devopsproject.jar devopsproject.jar
ENTRYPOINT ["java","-jar","/devopsproject.jar"]