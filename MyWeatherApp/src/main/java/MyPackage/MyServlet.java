package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

//import com.sun.tools.javac.parser.Scanner;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		String inputData = request.getParameter("userInput");
//		System.out.print(inputData);
		String apiKey = "dea67cfd6c58d4aa17f4ba2916607be7";
		//Get city from the form input 
		String city = request.getParameter("city");
		
		
		// ✅ Input validation
		
		if(city == null || city.trim().isEmpty()) {
			request.setAttribute("ERROR !!","Enter Valuid City Name ");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		city = city.trim();
		// Create URL for OpenWeatherMap API request
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
		
		
		//API integration
		URL url = new URL(apiUrl);
		
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		
		// ✅ Check HTTP response code
	    int status = connection.getResponseCode();
	    if (status != 200) {
	        request.setAttribute("error", "City not found or invalid input.");
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	        return;
	    }
		
		
		
		// Access DATA FROM API
		
		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		
		//store data form API
		StringBuilder responseContent = new StringBuilder();
		
		//to take input from user create scanner object
		Scanner scanner = new Scanner(reader);
		
		while(scanner.hasNext()) {
			responseContent.append(scanner.nextLine());
		}
		scanner.close();
		System.out.print(responseContent);
		
		//TYPECASTING = Parsing data into JSON
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
		System.out.print(jsonObject);
		
		//DATE AND TIME
		long dateTimeStamp = jsonObject.get("dt").getAsLong() * 1000;
		String date = new Date(dateTimeStamp).toString();
		
		//TEMPERATURE
		double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		int tempratureCelcius = (int) (temperatureKelvin - 273.15);
		
		//HUMIDITY
		int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
		
		//wind Speed
		double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		
		//Weather Condition
		String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		
		
		// Set Data to send it to JSP page
		
		request.setAttribute("date", date);
		request.setAttribute("city", city);
		request.setAttribute("temprature", tempratureCelcius);
		request.setAttribute("weatherCondition", weatherCondition);
		request.setAttribute("humidity", humidity);
		request.setAttribute("windSpeed", windSpeed);
		request.setAttribute("weatherData", responseContent.toString());
		
		connection.disconnect();
		 
		// Forward the request to the weather.jsp page for rendering
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		//doGet(request, response);
	}

}
