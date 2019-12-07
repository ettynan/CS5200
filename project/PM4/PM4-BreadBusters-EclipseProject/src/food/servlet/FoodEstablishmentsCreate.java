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

@WebServlet("/foodestablishmentscreate")
public class FoodEstablishmentsCreate extends HttpServlet {
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
        req.getRequestDispatcher("/FoodEstablishmentsCreate.jsp").forward(req, resp);
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
        	// Create the liquor estab.
        	String estabName = req.getParameter("estabname");
	        try {
	        	int establishmentKey = Integer.parseInt(establishmentKeyString);
	        	FoodEstablishments FE = new FoodEstablishments(establishmentKey, estabName);
	        	FE = foodEstablishmentsDao.create(FE);
	        	messages.put("success", "Successfully created Food Establishment for Establishment Name " + estabName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/FoodEstablishmentsCreate.jsp").forward(req, resp);
    }
}