# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /usr/src/app
# Copy the pom.xml and src folder into the container
COPY pom.xml .
COPY src ./src
# Build the application (skipping tests)
RUN mvn clean package -DskipTests

# Stage 2: Setup the runtime container
FROM openjdk:17-slim
WORKDIR /usr/app
# Copy the built jar from the build stage into the runtime container
COPY --from=build /usr/src/app/target/*.jar ./myapp.jar
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]
