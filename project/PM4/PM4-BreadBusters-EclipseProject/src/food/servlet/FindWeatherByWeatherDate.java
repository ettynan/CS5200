package food.servlet;

import food.dal.*;
import food.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findweatherbyweatherdate")
public class FindWeatherByWeatherDate extends HttpServlet {
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
        
        // Retrieve and validate weatherDate
        String weatherDateString = req.getParameter("weatherdate");
        if (weatherDateString == null || weatherDateString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid WeatherDate.");
        } else {
        	// Retrieve Weather and store as a message.
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
            	weather = weatherDao.getWeatherByWeatherDate(weatherDate);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + weatherDateString);
        	messages.put("previousWeatherDate", weatherDateString);
        }
        req.setAttribute("weather", weather);
        
        req.getRequestDispatcher("/FindWeatherByWeatherDate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Weather> weather = new ArrayList<Weather>();
        
        // Retrieve and validate weatherDate.
        String weatherDateString = req.getParameter("weatherdate");
        if (weatherDateString == null || weatherDateString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid WeatherDate.");
        } else {
        	// Retrieve Weather and store as a message.
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
            	weather = weatherDao.getWeatherByWeatherDate(weatherDate);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + weatherDateString);
        }
        req.setAttribute("weather", weather);
        
        req.getRequestDispatcher("/FindWeatherByWeatherDate.jsp").forward(req, resp);
    }
}