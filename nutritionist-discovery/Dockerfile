FROM openjdk:8-jre

ARG JAR_FILE=target/*.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/*.jar /opt/app/discovery-server.jar
COPY ${JAR_FILE} discovery-server.jar

ENTRYPOINT ["java","-jar","discovery-server.jar"]
