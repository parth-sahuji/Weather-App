# Weather-App

This Weather App is a Java-based dynamic web application that allows users to check the current weather of any city using a clean, interactive interface. Built with Java Servlets, JSP, and HTML/CSS, it demonstrates full-stack web development using traditional Java EE technologies.

Features
City Weather Search: Users can enter any city to get the current weather data.
Live Weather Integration: Connects to a weather API (via Gson) to fetch real-time data.
Animated UI: Includes typing animation, hover effects, and CSS transitions for a modern user experience.
Live Clock Widget: Displays current time in a fixed header using stylish formatting.
Responsive Design: Clean and centered layout with gradient backgrounds and shadows.



Technologies Used

Java Servlets
JSP (JavaServer Pages)
HTML5, CSS3
Gson (for JSON parsing)
Web.xml configuration for routing
Apache Tomcat (recommended server)


Project Structure

index.html: Entry point with input form and welcome animation
MyServlet.java: Backend logic to handle POST requests and fetch weather data
index.jsp: Dynamically displays the fetched weather details
style.css: Styling and animations
web.xml: Servlet mapping and welcome-file list
gson-2.8.5.jar: For handling JSON response from weather API
