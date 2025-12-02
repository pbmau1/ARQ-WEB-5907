# ---------------------------
#  ETAPA 1: BUILD CON MAVEN
# ---------------------------
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar SOLO la carpeta del backend
COPY moneyProject/ .

# Compilar usando maven wrapper
RUN ./mvnw clean package -DskipTests


# ---------------------------
#  ETAPA 2: RUNTIME
# ---------------------------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el jar generado desde la etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
