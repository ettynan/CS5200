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


@WebServlet("/licensesupdatelicenseexpiration")
public class LicensesUpdateLicenseExpiration extends HttpServlet {
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

        // Retrieve license key and validate.
        String licenseKeyString = req.getParameter("licensekey");
        if (licenseKeyString == null || licenseKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseKey.");
        } else {
        	try {
        		int licenseKeyInt = Integer.parseInt(licenseKeyString);
        		Licenses license = licensesDao.getLicenseByLicenseKey(licenseKeyInt);
        		if(license == null) {
        			messages.put("success", "LicenseKey does not exist.");
        		}
        		req.setAttribute("license", license);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LicensesUpdateLicenseExpiration.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve license key and validate.
        String licenseKeyString = req.getParameter("licensekey");
        if (licenseKeyString == null || licenseKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseKey.");
        } else {
        	try {
        		int licenseKeyInt = Integer.parseInt(licenseKeyString);
        		Licenses license = licensesDao.getLicenseByLicenseKey(licenseKeyInt);
        		if(license == null) {
        			messages.put("success", "LicenseKey does not exist. No update to perform.");
        		} else {
        			String newLicenseExpirationString = req.getParameter("licenseexpiration");
        			// Dates must be in the format yyyy-mm-dd.
                	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                	Date newLicenseExpiration = new Date();
                	try {
                		newLicenseExpiration = dateFormat.parse(newLicenseExpirationString);
                	} catch (ParseException e) {
                		e.printStackTrace();
        				throw new IOException(e);
                	}
        			if (newLicenseExpirationString == null || newLicenseExpirationString.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LicenseExpiration.");
        	        } else {
        	        	license = licensesDao.updateLicenseExpiration(license, newLicenseExpiration);
        	        	messages.put("success", "Successfully updated " + licenseKeyString);
        	        }
        		}
        		req.setAttribute("license", license);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/LicensesUpdateLicenseExpiration.jsp").forward(req, resp);
    }
}