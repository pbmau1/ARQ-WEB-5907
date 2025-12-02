# ---------------------------
#  ETAPA 1: BUILD CON MAVEN
# ---------------------------
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY moneyProject/ .

RUN chmod +x mvnw


RUN ./mvnw clean package -DskipTests


# ---------------------------
#  ETAPA 2: RUNTIME
# ---------------------------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
