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

@WebServlet("/liquorlicensesdelete")
public class LiquorLicensesDelete extends HttpServlet {
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
        messages.put("title", "Delete Liquor License");        
        req.getRequestDispatcher("/LiquorLicensesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate license key.
        String liquorLicenseKeyString = req.getParameter("liquorlicensekey");
        if (liquorLicenseKeyString == null || liquorLicenseKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid LiquorLicenseKey");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Liquor License.
        	int liquorLicenseKeyInt = Integer.parseInt(liquorLicenseKeyString);
	        LiquorLicenses liquorLicense = new LiquorLicenses(liquorLicenseKeyInt);	       
	        try {
	        	liquorLicense = liquorLicensesDao.getLiquorLicenseByLiquorLicenseKey(liquorLicenseKeyInt);
	        	liquorLicense = liquorLicensesDao.delete(liquorLicense);
	        	// Update the message.
		        if (liquorLicense == null) {
		            messages.put("title", "Successfully deleted " + liquorLicenseKeyString);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + liquorLicenseKeyString);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LiquorLicensesDelete.jsp").forward(req, resp);
    }
}