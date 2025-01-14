FROM openjdk:22-jdk
WORKDIR /app
COPY target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]