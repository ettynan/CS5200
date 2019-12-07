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


@WebServlet("/findlicensesbylicensenumber")
public class FindLicensesByLicenseNumber extends HttpServlet {
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
        
        // Retrieve and validate licenseNumber
        String licenseNumber = req.getParameter("licensenumber");
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseNumber.");
        } else {
        	// Retrieve License and store as a message.
        	try {
            	licenses = licensesDao.getLicensesByLicenseNumber(licenseNumber);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseNumber);
        	messages.put("previousLicense", licenseNumber);
        }
        req.setAttribute("licenses", licenses);
        
        req.getRequestDispatcher("/FindLicensesByLicenseNumber.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Licenses> licenses = new ArrayList<Licenses>();
        
        // Retrieve and validate licenseNumber.
        String licenseNumber = req.getParameter("licensenumber");
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseNumber.");
        } else {
        	// Retrieve License and store as a message.
        	try {
            	licenses = licensesDao.getLicensesByLicenseNumber(licenseNumber);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseNumber);
        }
        req.setAttribute("licenses", licenses);
        
        req.getRequestDispatcher("/FindLicensesByLicenseNumber.jsp").forward(req, resp);
    }
}