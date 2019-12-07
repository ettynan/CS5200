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

@WebServlet("/liquorlicensescreate")
public class LiquorLicensesCreate extends HttpServlet {
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
        req.getRequestDispatcher("/LiquorLicensesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate liquorLicense.
        String liquorLicenseKeyString = req.getParameter("liquorlicensekey");
        if (liquorLicenseKeyString == null || liquorLicenseKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the LiquorLicense.
        	String liquorLicenseComments = req.getParameter("liquorlicensecomments");
        	String liquorLocationComments = req.getParameter("liquorlocationcomments");
        	String licenseNumber = req.getParameter("licensenumber");
        	String licenseFKString = req.getParameter("licensefk");
	        try {
	        	int liquorLicenseKey = Integer.parseInt(liquorLicenseKeyString);
	        	int licenseFK = Integer.parseInt(licenseFKString);
	        	LiquorLicenses liquorLicense = new LiquorLicenses(liquorLicenseKey, liquorLicenseComments, liquorLocationComments, licenseFK, licenseNumber);
	        	liquorLicense = liquorLicensesDao.create(liquorLicense);
	        	messages.put("success", "Successfully created " + liquorLicenseKey);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        req.getRequestDispatcher("/LiquorLicensesCreate.jsp").forward(req, resp);
    }
}