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

@WebServlet("/licensecategoriescreate")
public class LicenseCategoriesCreate extends HttpServlet {
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
        req.getRequestDispatcher("/LicenseCategoriesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate category.
        String licenseCategory = req.getParameter("licensecategory");
        if (licenseCategory == null || licenseCategory.trim().isEmpty()) {
            messages.put("success", "Invalid LicenseCategory");
        } else {
        	// Create the LicenseCategory.
        	String licenseCategoryDescription = req.getParameter("licensecategorydescription");
	        try {
	        	LicenseCategories licenseCategories = new LicenseCategories(licenseCategory, licenseCategoryDescription);
	        	licenseCategories = licenseCategoriesDao.create(licenseCategories);
	        	messages.put("success", "Successfully created " + licenseCategory);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LicenseCategoriesCreate.jsp").forward(req, resp);
    }
}