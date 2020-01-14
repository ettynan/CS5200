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


@WebServlet("/findaddressesbykey")
public class FindAddressesByKey extends HttpServlet {
	
	protected AddressesDao addressesDao;
	
	@Override
	public void init() throws ServletException {
		addressesDao = AddressesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Addresses address = null;
        
        // Retrieve and validate establishment key.
        String estabKeyString = req.getParameter("estabkey");
        if (estabKeyString == null || estabKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Establishment Key.");
        } else {
        	// Retrieve Establishments, and store as a message.
        	try {
        		int estabKey = Integer.parseInt(estabKeyString);
            	address = addressesDao.getAddressByKey(estabKey);
            	if(address == null) {
        			messages.put("success", "Key does not exist.");
        		}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Address result for Key " + estabKeyString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindAddressesByKey.jsp.
        	messages.put("previousAddress", estabKeyString);
        }
        req.setAttribute("address", address);
        
        req.getRequestDispatcher("/FindAddressesByKey.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Addresses address = null;
        
     // Retrieve and validate establishment key.
        String estabKeyString = req.getParameter("estabkey");
        if (estabKeyString == null || estabKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Establishment Key.");
        } else {
        	// Retrieve Establishments, and store as a message.
        	try {
        		int estabKey = Integer.parseInt(estabKeyString);
            	address = addressesDao.getAddressByKey(estabKey);
            	if(address == null) {
        			messages.put("success", "Key does not exist.");
        		}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Address result for Key " + estabKeyString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindAddressesByKey.jsp.        	
        }
        req.setAttribute("address", address);
        
        req.getRequestDispatcher("/FindAddressesByKey.jsp").forward(req, resp);
	}
}