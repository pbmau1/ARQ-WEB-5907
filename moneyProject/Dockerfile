# ---------------------------
#  ETAPA 1: BUILD CON MAVEN
# ---------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copiar pom y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto y generar el JAR
RUN mvn clean package -DskipTests

# ---------------------------
#  ETAPA 2: RUNTIME
# ---------------------------
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar el JAR generado desde la etapa builder
COPY --from=builder /app/target/*.jar app.jar

# Exponer puerto 8080
EXPOSE 8080

# Comando para iniciar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
