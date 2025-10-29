# ---- Stage 1: Build the application using Maven ----
# Use an official Maven image that includes JDK 21 (matching your pom.xml)
FROM maven:3.9-eclipse-temurin-21-jammy AS build

# Set the working directory inside the container
WORKDIR /app

# Copy only the pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .

# Download dependencies (this layer is cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Copy the rest of your source code
COPY src ./src

# Build the application JAR file, skipping tests
RUN mvn package -DskipTests

# ---- Stage 2: Create the final, lightweight image ----
# Use an official JRE image (Java Runtime Environment) based on Ubuntu Jammy
FROM eclipse-temurin:21-jre-jammy

# Set the working directory
WORKDIR /app

# Copy ONLY the built JAR file from the 'build' stage
# It finds the JAR in target/ and renames it to app.jar
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
