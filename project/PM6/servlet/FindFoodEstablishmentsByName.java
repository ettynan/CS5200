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


@WebServlet("/findfoodestablishmentsbyname")
public class FindFoodEstablishmentsByName extends HttpServlet {
	
	protected FoodEstablishmentsDao foodEstablishmentsDao;
	
	@Override
	public void init() throws ServletException {
		foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        FoodEstablishments FE = null;
        
        // Retrieve and validate Food Establishment Name.
        String estabName = req.getParameter("estabname");
        if (estabName == null || estabName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Establishment Name.");
        } else {
        	// Retrieve Food Establishment, and store as a message.
        	try {
            	FE = foodEstablishmentsDao.getFoodEstablishmentByName(estabName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + estabName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindFoodEstablishmentsByName.jsp.
        	messages.put("previousFoodEstablishment", estabName);
        }
        req.setAttribute("FE", FE);
        
        req.getRequestDispatcher("/FindFoodEstablishmentsByName.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        FoodEstablishments FE = null;
        
        // Retrieve and validate Food Establishment Name.
        String estabName = req.getParameter("estabname");
        if (estabName == null || estabName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Establishment Name.");
        } else {
        	// Retrieve Food Establishment, and store as a message.
        	try {
            	FE = foodEstablishmentsDao.getFoodEstablishmentByName(estabName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + estabName);
        	
        }
        req.setAttribute("FE", FE);
        
        req.getRequestDispatcher("/FindFoodEstablishmentsByName.jsp").forward(req, resp);
	}
}