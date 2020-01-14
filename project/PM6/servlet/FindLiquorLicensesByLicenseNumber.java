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


@WebServlet("/findliquorlicensesbylicensenumber")
public class FindLiquorLicensesByLicenseNumber extends HttpServlet {
protected LiquorLicensesDao liquorLicensesDao;
	
	@Override
	public void init() throws ServletException {
		liquorLicensesDao = LiquorLicensesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<LiquorLicenses> liquorLicenses = new ArrayList<LiquorLicenses>();
        
        // Retrieve and validate licenseNumber
        String licenseNumber = req.getParameter("licensenumber");
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseNumber.");
        } else {
        	// Retrieve LiquorLicense and store as a message.
        	try {
            	liquorLicenses = liquorLicensesDao.getLiquorLicensesByLicenseNumber(licenseNumber);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseNumber);
        	messages.put("previousLiquorLicense", licenseNumber);
        }
        req.setAttribute("liquorLicenses", liquorLicenses);
        
        req.getRequestDispatcher("/FindLiquorLicensesByLicenseNumber.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<LiquorLicenses> liquorLicenses = new ArrayList<LiquorLicenses>();
        
        // Retrieve and validate licenseNumber.
        String licenseNumber = req.getParameter("licensenumber");
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            messages.put("success", "Please enter a valid LicenseNumber.");
        } else {
        	// Retrieve LiquorLicense and store as a message.
        	try {
            	liquorLicenses = liquorLicensesDao.getLiquorLicensesByLicenseNumber(licenseNumber);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + licenseNumber);
        }
        req.setAttribute("liquorLicenses", liquorLicenses);
        
        req.getRequestDispatcher("/FindLiquorLicensesByLicenseNumber.jsp").forward(req, resp);
    }
}