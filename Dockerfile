# Use official Tomcat image
FROM tomcat:9.0-jdk17

# Clean default apps
RUN rm -rf /usr/local/tomcat/webapps/*

# ✅ Copy your JSP/HTML files to Tomcat's webapps/ROOT
COPY ./MyWeatherApp/WebContent/ /usr/local/tomcat/webapps/ROOT/

# Expose port
EXPOSE 8080

# Run Tomcat
CMD ["catalina.sh", "run"]
