FROM adoptopenjdk/openjdk11:alpine
VOLUME /tmp
COPY target/*.jar twitter-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/twitter-0.0.1-SNAPSHOT.jar"]