# Use official Tomcat image with JDK 17
FROM tomcat:9.0-jdk17

# Clean default webapps (like ROOT)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your project files (like WebContent folder) into Tomcat’s ROOT
COPY ./WebContent/ /usr/local/tomcat/webapps/ROOT/

# Expose port 8080
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]
