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

@WebServlet("/addressescreate")
public class AddressesCreate extends HttpServlet {
	protected AddressesDao addressesDao;
	
	@Override
	public void init() throws ServletException {
		addressesDao = AddressesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages); 
        req.getRequestDispatcher("/AddressesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate category.
        String establishmentKeyString = req.getParameter("establishmentkey");
        if (establishmentKeyString == null || establishmentKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid EstablishmentKey");
        } else {
        	// Create the Address.
        	String street = req.getParameter("street");
        	String city = req.getParameter("city");
        	String state = req.getParameter("state");
        	String zipString = req.getParameter("zip");
        	String propertyIdString = req.getParameter("propertyid");
	        try {
	        	int establishmentKey = Integer.parseInt(establishmentKeyString);
	        	int zip = Integer.parseInt(zipString);
	        	int propertyId = Integer.parseInt(propertyIdString);
	        	Addresses address = new Addresses(establishmentKey, street, city, state, zip, propertyId);
	        	address = addressesDao.create(address);
	        	messages.put("success", "Successfully created Address for EstablishmentKey " + establishmentKey);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/AddressesCreate.jsp").forward(req, resp);
    }
}