<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String condition = (String) request.getAttribute("weatherCondition"); // Note the case sensitivity
    String emoji = "🌈";

    if (condition != null) {
        switch(condition.toLowerCase()) {
            case "clear": emoji = "☀️"; break;
            case "clouds": emoji = "☁️"; break;
            case "rain": emoji = "🌧️"; break;
            case "thunderstorm": emoji = "⛈️"; break;
            case "snow": emoji = "❄️"; break;
            case "mist":
            case "haze":
            case "fog": emoji = "🌫️"; break;
            default: emoji = "🌈";
        }
    }

    request.setAttribute("weatherEmoji", emoji);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Report</title>
	<link rel="stylesheet" href="style.css" />

</head>
<body>
<div class="container">


		<% String error = (String) request.getAttribute("error"); %>
		<% if (error != null) { %>
		    <p style="color: red;"><%= error %></p>
		<% } %>





        <h1>🌤️ Weather Report for ${city}</h1>
        <p><strong>Date & Time:</strong> ${date}</p>
        <p class="temp">${temprature} °C</p>
        <p><strong>Condition:</strong> ${weatherCondition} <span class="emoji">${weatherEmoji}</span></p>
        <p><strong>Humidity:</strong> ${humidity}% 💧</p>
        <p><strong>Wind Speed:</strong> ${windSpeed} m/s 💨</p>
    </div>

</body>
</html>