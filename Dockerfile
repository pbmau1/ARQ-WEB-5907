FROM maven:3.9.11-eclipse-temurin-24 AS builder
WORKDIR /app
COPY moneyProject/ .
RUN mvn clean package -DskipTests

FROM openjdk:24-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]