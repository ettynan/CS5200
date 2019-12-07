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

@WebServlet("/licensecategoriesdelete")
public class LicenseCategoriesDelete extends HttpServlet {
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
        messages.put("title", "Delete LicenseCategory");        
        req.getRequestDispatcher("/LicenseCategoriesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate category name.
        String licenseCategory = req.getParameter("licensecategory");
        if (licenseCategory == null || licenseCategory.trim().isEmpty()) {
            messages.put("title", "Invalid LicenseCategory");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the LicenseCategory.
	        LicenseCategories licenseCategories = new LicenseCategories(licenseCategory);
	        try {
	        	licenseCategories = licenseCategoriesDao.delete(licenseCategories);
	        	// Update the message.
		        if (licenseCategories == null) {
		            messages.put("title", "Successfully deleted " + licenseCategory);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + licenseCategory);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LicenseCategoriesDelete.jsp").forward(req, resp);
    }
}