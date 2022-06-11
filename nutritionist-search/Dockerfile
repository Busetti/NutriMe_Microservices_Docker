FROM openjdk:8-jre

ARG JAR_FILE=target/*.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/*.jar /opt/app/search-service.jar
COPY ${JAR_FILE} search-service.jar

ENTRYPOINT ["java","-jar","search-service.jar"]
