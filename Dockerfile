FROM openjdk:17-alpine

ADD target/bank-application-0.0.1-SNAPSHOT.jar bank-application-backend.jar

ENTRYPOINT ["java", "-jar", "bank-application-backend.jar"]
