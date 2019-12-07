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

@WebServlet("/addressesupdate")
public class AddressesUpdate extends HttpServlet {
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
     // Retrieve Key and validate.
        String establishmentKeyString = req.getParameter("establishmentkey");
        if (establishmentKeyString == null || establishmentKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid EstablishmentKey");
        } else {
        	try {
        		int establishmentKey = Integer.parseInt(establishmentKeyString);
        		Addresses address = addressesDao.getAddressByKey(establishmentKey);
        		if(address == null) {
        			messages.put("success", "EstablishmentKey does not exist.");
        		}
        		req.setAttribute("address", address);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddressesUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String establishmentKeyString = req.getParameter("establishmentkey");
        if (establishmentKeyString == null || establishmentKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid EstablishmentKey");
        } else {
        	try {
        		String newStreet = req.getParameter("street");
        		String newCity = req.getParameter("city");
        		String newState = req.getParameter("state");
        		String newZipString = req.getParameter("zip");
        		String newPropertyIdString = req.getParameter("propertyid");
        		int establishmentKey = Integer.parseInt(establishmentKeyString);
        		int newZip = Integer.parseInt(newZipString);
        		int newPropertyId = Integer.parseInt(newPropertyIdString);
        		Addresses address = addressesDao.updateAddress(establishmentKey, newStreet, newCity, newState, newZip, newPropertyId);        		
        		messages.put("success", "Successfully updated Address for Establishment Key " + establishmentKey);
        		
        		req.setAttribute("address", address);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AddressesUpdate.jsp").forward(req, resp);
	}
}