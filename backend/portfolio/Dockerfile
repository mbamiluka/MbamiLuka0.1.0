# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build

# Set working directory to the directory where pom.xml is located
WORKDIR /usr/src/app

# Copy pom.xml and src/ to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Setup the runtime container
FROM openjdk:17-slim

# Copy the built jar file from the build stage
COPY --from=build /usr/src/app/target/*.jar /usr/app/myapp.jar

EXPOSE 8080
WORKDIR /usr/app
CMD ["java", "-jar", "myapp.jar"]
