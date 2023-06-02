FROM openjdk
ARG JAR_FILE=target/EasyBotTestTask-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY target/EasyBotTestTask-0.0.1-SNAPSHOT.jar /app/EasyBotTestTask-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "EasyBotTestTask-0.0.1-SNAPSHOT.jar"]