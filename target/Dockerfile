# Use OpenJDK 17 JDK slim version as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the leaderboard.war file into the container
COPY leaderboard.war app.war

# Expose port 8080 to the outside world
EXPOSE 8082

# Start the application
CMD ["java", "-jar", "app.war"]
