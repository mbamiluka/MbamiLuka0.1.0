# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build

# Set the working directory
WORKDIR /usr/src/app

# Copy the project files to the container
COPY . /usr/src/app

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Setup the runtime container
FROM openjdk:17-slim

# Copy the built jar file from the build stage
COPY --from=build /usr/src/app/target/*.jar /usr/app/myapp.jar

EXPOSE 8080

# Set the working directory for the runtime container
WORKDIR /usr/app

# Command to run the application
CMD ["java", "-jar", "myapp.jar"]
