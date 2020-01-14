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

@WebServlet("/licensesdelete")
public class LicensesDelete extends HttpServlet {
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
        messages.put("title", "Delete License");        
        req.getRequestDispatcher("/LicensesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate license key.
        String licenseKeyString = req.getParameter("licensekey");
        if (licenseKeyString == null || licenseKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid LicenseKey");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the License.   
	        try {
	        	int licenseKeyInt = Integer.parseInt(licenseKeyString);
		        Licenses license = new Licenses(licenseKeyInt);	    
	        	license = licensesDao.getLicenseByLicenseKey(licenseKeyInt);
	        	license = licensesDao.delete(license);
	        	// Update the message.
		        if (license == null) {
		            messages.put("title", "Successfully deleted " + licenseKeyString);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + licenseKeyString);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LicensesDelete.jsp").forward(req, resp);
    }
}