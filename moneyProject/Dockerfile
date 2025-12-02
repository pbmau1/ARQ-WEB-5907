
COPY . .
RUN mvn dependency:go-offline -B


RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
