# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build

# Set the working directory to the directory containing the pom.xml
WORKDIR /usr/src/app/backend

# Copy the project files to the container (adjust the source directory as needed)
COPY backend /usr/src/app/backend

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Setup the runtime container
FROM openjdk:17-slim

# Copy the built jar file from the build stage
COPY --from=build /usr/src/app/backend/target/*.jar /usr/app/myapp.jar

EXPOSE 8080

WORKDIR /usr/app

CMD ["java", "-jar", "myapp.jar"]
