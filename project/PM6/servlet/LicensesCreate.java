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

@WebServlet("/licensescreate")
public class LicensesCreate extends HttpServlet {
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
        req.getRequestDispatcher("/LicensesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate license.
        String licenseKeyString = req.getParameter("licensekey");
        if (licenseKeyString == null || licenseKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the License.
        	String licenseNumber = req.getParameter("licensenumber");
        	String licenseIssueString = req.getParameter("licenseissue");
        	String licenseExpirationString = req.getParameter("licenseexpiration");
        	Licenses.LicenseStatus licenseStatus = Licenses.LicenseStatus.valueOf(req.getParameter("licensestatus"));
        	String licenseCategoryFK = req.getParameter("licensecategoryfk");
        	// Dates must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date licenseIssue = new Date();
        	Date licenseExpiration = new Date();
        	try {
        		licenseIssue = dateFormat.parse(licenseIssueString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	try {
        		licenseExpiration = dateFormat.parse(licenseExpirationString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	int licenseKey = Integer.parseInt(licenseKeyString);
	        	Licenses license = new Licenses(licenseKey, licenseNumber, licenseIssue, licenseExpiration, licenseStatus, licenseCategoryFK);
	        	license = licensesDao.create(license);
	        	messages.put("success", "Successfully created " + licenseKey);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }     
        req.getRequestDispatcher("/LicensesCreate.jsp").forward(req, resp);
    }
}