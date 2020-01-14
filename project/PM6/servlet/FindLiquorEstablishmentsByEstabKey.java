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


@WebServlet("/findliquorestablishmentsbyestabkey")
public class FindLiquorEstablishmentsByEstabKey extends HttpServlet {
	
	protected LiquorEstablishmentsDao liquorEstablishmentsDao;
	
	@Override
	public void init() throws ServletException {
		liquorEstablishmentsDao = LiquorEstablishmentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        LiquorEstablishments LE = null;
        
        // Retrieve and validate liquor establishment key.
        String estabKeyString = req.getParameter("estabkey");
        if (estabKeyString == null || estabKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Establishment Key.");
        } else {
        	// Retrieve liquorestablishments, and store as a message.
        	try {
        		int estabKey = Integer.parseInt(estabKeyString);
            	LE = liquorEstablishmentsDao.getLiquorEstablishmentByEstablishmentKey(estabKey);
            	if(LE == null) {
        			messages.put("success", "Key does not exist.");
        		}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Liquor Establishment result for Establishment Key " + estabKeyString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindLiquorEstablishmentsByEstabKey.jsp.
        	messages.put("previousLiquorEstablishments", estabKeyString);
        }
        req.setAttribute("LE", LE);
        
        req.getRequestDispatcher("/FindLiquorEstablishmentsByEstabKey.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

LiquorEstablishments LE = null;
        
        // Retrieve and validate liquor establishment key.
        String estabKeyString = req.getParameter("estabkey");
        if (estabKeyString == null || estabKeyString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Establishment Key.");
        } else {
        	// Retrieve liquorestablishments, and store as a message.
        	try {
        		int estabKey = Integer.parseInt(estabKeyString);
            	LE = liquorEstablishmentsDao.getLiquorEstablishmentByEstablishmentKey(estabKey);
            	if(LE == null) {
        			messages.put("success", "Key does not exist.");
        		}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying Liquor Establishment result for Establishment Key " + estabKeyString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindLiquorEstablishmentsByEstabKey.jsp.
        }
        req.setAttribute("LE", LE);
        
        req.getRequestDispatcher("/FindLiquorEstablishmentsByEstabKey.jsp").forward(req, resp);
	}
}