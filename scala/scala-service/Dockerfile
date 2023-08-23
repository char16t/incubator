FROM amazoncorretto:20-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the build files to the container
COPY target/demo.jar /app

# Run the application
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]
