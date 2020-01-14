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


@WebServlet("/licensesupdatelicensenumber")
public class LicensesUpdateLicenseNumber extends HttpServlet {
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
        req.getRequestDispatcher("/LicensesUpdateLicenseNumber.jsp").forward(req, resp);
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
        			String newLicenseNumber = req.getParameter("licensenumber");
        			if (newLicenseNumber == null || newLicenseNumber.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid LicenseNumber.");
        	        } else {
        	        	license = licensesDao.updateLicenseNumber(license, newLicenseNumber);
        	        	messages.put("success", "Successfully updated " + licenseKeyString);
        	        }
        		}
        		req.setAttribute("license", license);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/LicensesUpdateLicenseNumber.jsp").forward(req, resp);
    }
}