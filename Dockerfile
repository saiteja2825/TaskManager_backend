# ----------------------------
# Stage 1: Build the Spring Boot app
# ----------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy project files
COPY . .

# Build the project and skip tests
RUN mvn clean package -DskipTests

# ----------------------------
# Stage 2: Run the Spring Boot app
# ----------------------------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR built in the previous stage (match any JAR)
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
