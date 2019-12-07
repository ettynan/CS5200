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

@WebServlet("/foodlicensesdelete")
public class FoodLicensesDelete extends HttpServlet {
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
        messages.put("title", "Delete Food License");        
        req.getRequestDispatcher("/FoodLicensesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate license key.
        String foodLicenseKeyString = req.getParameter("foodlicensekey");
        if (foodLicenseKeyString == null || foodLicenseKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid FoodLicenseKey");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Food License.
        	int foodLicenseKeyInt = Integer.parseInt(foodLicenseKeyString);
	        FoodLicenses foodLicense = new FoodLicenses(foodLicenseKeyInt);	       
	        try {
	        	foodLicense = foodLicensesDao.getFoodLicenseByFoodLicenseKey(foodLicenseKeyInt);
	        	foodLicense = foodLicensesDao.delete(foodLicense);
	        	// Update the message.
		        if (foodLicense == null) {
		            messages.put("title", "Successfully deleted " + foodLicenseKeyString);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + foodLicenseKeyString);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/FoodLicensesDelete.jsp").forward(req, resp);
    }
}