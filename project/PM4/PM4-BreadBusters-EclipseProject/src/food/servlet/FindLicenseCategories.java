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


@WebServlet("/findlicensecategories")
public class FindLicenseCategories extends HttpServlet {
	protected LicenseCategoriesDao licenseCategoriesDao;
	
	@Override
	public void init() throws ServletException {
		licenseCategoriesDao = LicenseCategoriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        LicenseCategories licenseCategories = null;
        
        // Retrieve and validate license category.
        String licenseCategory = req.getParameter("licensecategory");
        if (licenseCategory == null || licenseCategory.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseCategory.");
        } else {
        	// Retrieve LicenseCategories and store as a message.
        	try {
            	licenseCategories = licenseCategoriesDao.getLicenseCategoriesByLicenseCategory(licenseCategory);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseCategory);
        	messages.put("previousLicenseCategory", licenseCategory);
        }
        req.setAttribute("licenseCategories", licenseCategories);
        
        req.getRequestDispatcher("/FindLicenseCategories.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        LicenseCategories licenseCategories = null;
        
        // Retrieve and validate licenseCategory.
        String licenseCategory = req.getParameter("licensecategory");
        if (licenseCategory == null || licenseCategory.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseCategory.");
        } else {
        	// Retrieve LicenseCategory and store as a message.
        	try {
            	licenseCategories = licenseCategoriesDao.getLicenseCategoriesByLicenseCategory(licenseCategory);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseCategory);
        }
        req.setAttribute("licenseCategories", licenseCategories);
        
        req.getRequestDispatcher("/FindLicenseCategories.jsp").forward(req, resp);
    }
}