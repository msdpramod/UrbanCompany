# Use a platform-compatible Temurin image
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the JAR built by Maven into the container
COPY target/UrbanCompany-0.0.1-SNAPSHOT.jar app.jar

# Expose port (change if your app uses a different one)
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker run -d -p 8080:8080 urbancompany:latest
#  docker ps
#  docker logs -f 21e00ea5fd08
# docker stop 21e00ea5fd08
