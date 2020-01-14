package food.servlet;

import food.dal.*;
import food.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findweatherbyweatherevents")
public class FindWeatherByWeatherEvents extends HttpServlet {
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
        
        List<Weather> weather = new ArrayList<Weather>();
        
        // Retrieve and validate weatherEvent
        String weatherEventString = req.getParameter("weatherevent");
        if (weatherEventString == null || weatherEventString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid WeatherEvent.");
        } else {
        	// Retrieve Weather and store as a message.
        	Weather.Events weatherEvent = Weather.Events.valueOf(req.getParameter("weatherevent"));
        	try {
            	weather = weatherDao.getWeatherByWeatherEvents(weatherEvent);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + weatherEvent.name());
        	messages.put("previousWeatherEvent", weatherEvent.name());
        }
        req.setAttribute("weather", weather);
        
        req.getRequestDispatcher("/FindWeatherByWeatherEvents.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Weather> weather = new ArrayList<Weather>();
        
        // Retrieve and validate weatherEvent.
        String weatherEventString = req.getParameter("weatherevent");
        if (weatherEventString == null || weatherEventString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid WeatherEvent.");
        } else {
        	// Retrieve Weather and store as a message.
        	Weather.Events weatherEvent = Weather.Events.valueOf(req.getParameter("weatherevent"));
        	try {
            	weather = weatherDao.getWeatherByWeatherEvents(weatherEvent);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + weatherEvent.name());
        }
        req.setAttribute("weather", weather);
        
        req.getRequestDispatcher("/FindWeatherByWeatherEvents.jsp").forward(req, resp);
    }
}