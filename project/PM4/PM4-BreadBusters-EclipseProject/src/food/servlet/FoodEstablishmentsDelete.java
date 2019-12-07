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

@WebServlet("/foodestablishmentsdelete")
public class FoodEstablishmentsDelete extends HttpServlet {
	protected FoodEstablishmentsDao foodEstablishmentsDao;
	
	@Override
	public void init() throws ServletException {
		foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("title", "Delete Food Establishment");        
        req.getRequestDispatcher("/FoodEstablishmentsDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate establishment.
        String estabName = req.getParameter("estabname");
        if (estabName == null || estabName.trim().isEmpty()) {
            messages.put("title", "Invalid Establishment Name");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Establishment.   
	        try {
		        FoodEstablishments FE = new FoodEstablishments(estabName);	    
	        	FE = foodEstablishmentsDao.deleteByName(estabName);
	        	// Update the message.
		        if (FE == null) {
		            messages.put("title", "Successfully deleted " + estabName);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + estabName);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/FoodEstablishmentsDelete.jsp").forward(req, resp);
    }
}