# Use an official OpenJDK runtime as a base image
FROM adoptopenjdk:17-jdk-hotspot-bionic

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/your-application.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]
