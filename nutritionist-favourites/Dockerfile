FROM openjdk:8-jre

ARG JAR_FILE=target/*.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/*.jar /opt/app/favourites-service.jar
COPY ${JAR_FILE} favourites-service.jar

ENTRYPOINT ["java","-jar","favourites-service.jar"]
