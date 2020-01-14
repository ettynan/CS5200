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

@WebServlet("/addressesdelete")
public class AddressesDelete extends HttpServlet {
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
        messages.put("title", "Delete Address");        
        req.getRequestDispatcher("/AddressesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate establishment key.
        String estabKeyString = req.getParameter("estabkey");
        if (estabKeyString == null || estabKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid Establishment Key");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Establishment.   
	        try {
	        	int estabKey = Integer.parseInt(estabKeyString);
		        Addresses address = new Addresses(estabKey);	    
	        	address = addressesDao.deleteByKey(estabKey);
	        	// Update the message.
		        if (address == null) {
		            messages.put("title", "Successfully deleted address for key " + estabKey);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete address for key " + estabKey);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/AddressesDelete.jsp").forward(req, resp);
    }
}