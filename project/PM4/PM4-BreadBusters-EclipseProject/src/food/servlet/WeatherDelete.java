package food.servlet;

import food.dal.*;
import food.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/weatherdelete")
public class WeatherDelete extends HttpServlet {
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
        messages.put("title", "Delete Weather");        
        req.getRequestDispatcher("/WeatherDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate license key.
        String weatherKeyString = req.getParameter("weatherkey");
        if (weatherKeyString == null || weatherKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid WeatherKey");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the License.
        	int weatherKeyInt = Integer.parseInt(weatherKeyString);
	        Weather weather = new Weather(weatherKeyInt);	       
	        try {
	        	weather = weatherDao.getWeatherByWeatherKey(weatherKeyInt);
	        	weather = weatherDao.delete(weather);
	        	// Update the message.
		        if (weather == null) {
		            messages.put("title", "Successfully deleted " + weatherKeyString);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + weatherKeyString);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/WeatherDelete.jsp").forward(req, resp);
    }
}