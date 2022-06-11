FROM openjdk:8-jre

ARG JAR_FILE=target/*.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/*.jar /opt/app/gateway-service.jar
COPY ${JAR_FILE} gateway-service.jar

ENTRYPOINT ["java","-jar","gateway-service.jar"]
