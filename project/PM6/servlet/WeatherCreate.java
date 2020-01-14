package food.servlet;

import food.dal.*;
import food.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/weathercreate")
public class WeatherCreate extends HttpServlet {
	protected WeatherDao weatherDao;
	
	@Override
	public void init() throws ServletException {
		weatherDao = WeatherDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages); 
        req.getRequestDispatcher("/WeatherCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate weather key.
        String weatherDateString = req.getParameter("weatherdate");
        if (weatherDateString == null || weatherDateString.trim().isEmpty()) {
            messages.put("success", "Invalid WeatherDAte");
        } else {
        	// Create the Weather.
        	String tempHighInFString = req.getParameter("temphighinf");
        	String tempAverageInFString = req.getParameter("tempaverageinf");
        	String tempLowInFString = req.getParameter("templowinf");
        	String humidityHighPercentageString = req.getParameter("humidityhighpercentage");
        	String humidityAveragePercentageString = req.getParameter("humidityaveragepercentage");
        	String humidityLowPercentageString = req.getParameter("humiditylowpercentage");
        	String windHighInMphString = req.getParameter("windhighinmph");
        	String windAverageInMphString = req.getParameter("windaverageinmph");
        	String windGustHighInMphString = req.getParameter("windgusthighinmph");
        	String snowFallInInchesString = req.getParameter("snowfallininches");
        	String precipitationInInchesString = req.getParameter("precipitationininches");
        	Weather.Events events = Weather.Events.valueOf(req.getParameter("events"));
        	// Dates must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date weatherDate = new Date();
        	try {
        		weatherDate = dateFormat.parse(weatherDateString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	int tempHighInF = Integer.parseInt(tempHighInFString);
	        	int tempAverageInF = Integer.parseInt(tempAverageInFString);
	        	int tempLowInF = Integer.parseInt(tempLowInFString);
	        	int humidityHighPercentage = Integer.parseInt(humidityHighPercentageString);
	        	int humidityAveragePercentage = Integer.parseInt(humidityAveragePercentageString);
	        	int humidityLowPercentage = Integer.parseInt(humidityLowPercentageString);
	        	int windHighInMph = Integer.parseInt(windHighInMphString);
	        	int windAverageInMph = Integer.parseInt(windAverageInMphString);
	        	int windGustHighInMph = Integer.parseInt(windGustHighInMphString);
	        	double snowFallInInches = Double.parseDouble(snowFallInInchesString);
	        	double precipitationInInches = Double.parseDouble(precipitationInInchesString);
	        	Weather weather = new Weather(weatherDate, tempHighInF, tempAverageInF, tempLowInF, humidityHighPercentage, humidityAveragePercentage, 
	        			humidityLowPercentage, windHighInMph, windAverageInMph, windGustHighInMph, snowFallInInches, precipitationInInches, events);
	        	weather = weatherDao.create(weather);
	        	messages.put("success", "Successfully created " + weatherDateString);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/WeatherCreate.jsp").forward(req, resp);
    }
}