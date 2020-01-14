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


@WebServlet("/findlicensesbylicensecategory")
public class FindLicensesByLicenseCategory extends HttpServlet {
protected LicensesDao licensesDao;
	
	@Override
	public void init() throws ServletException {
		licensesDao = LicensesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Licenses> licenses = new ArrayList<Licenses>();
        
        // Retrieve and validate licenseCategory
        String licenseCategory = req.getParameter("licensecategory");
        if (licenseCategory == null || licenseCategory.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseCategory.");
        } else {
        	// Retrieve License and store as a message.
        	try {
            	licenses = licensesDao.getLicensesByLicenseCategoryFK(licenseCategory);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseCategory);
        	messages.put("previousLicense", licenseCategory);
        }
        req.setAttribute("licenses", licenses);
        
        req.getRequestDispatcher("/FindLicensesByLicenseCategory.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Licenses> licenses = new ArrayList<Licenses>();
        
        // Retrieve and validate licenseCategory.
        String licenseCategory = req.getParameter("licensecategory");
        if (licenseCategory == null || licenseCategory.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseCategory.");
        } else {
        	// Retrieve License and store as a message.
        	try {
            	licenses = licensesDao.getLicensesByLicenseCategoryFK(licenseCategory);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseCategory);
        }
        req.setAttribute("licenses", licenses);
        
        req.getRequestDispatcher("/FindLicensesByLicenseCategory.jsp").forward(req, resp);
    }
}