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

@WebServlet("/foodlicensescreate")
public class FoodLicensesCreate extends HttpServlet {
	protected FoodLicensesDao foodLicensesDao;
	
	@Override
	public void init() throws ServletException {
		foodLicensesDao = FoodLicensesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/FoodLicensesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate foodLicense.
        String foodLicenseKeyString = req.getParameter("foodlicensekey");
        if (foodLicenseKeyString == null || foodLicenseKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid FoodLicenseKey");
        } else {
        	// Create the FoodLicense.
        	String licenseFKString = req.getParameter("licensefk");
        	String foodLicenseNumber = req.getParameter("licensenumber");
	        try {
	        	int foodLicenseKey = Integer.parseInt(foodLicenseKeyString);
	        	int licenseFK = Integer.parseInt(licenseFKString);
	        	FoodLicenses foodLicense = new FoodLicenses(foodLicenseKey, licenseFK, foodLicenseNumber);
	        	foodLicense = foodLicensesDao.create(foodLicense);
	        	messages.put("success", "Successfully created " + foodLicenseKeyString);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        req.getRequestDispatcher("/FoodLicensesCreate.jsp").forward(req, resp);
    }
}