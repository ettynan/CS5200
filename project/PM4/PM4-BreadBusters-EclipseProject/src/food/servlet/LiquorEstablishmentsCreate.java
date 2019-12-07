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

@WebServlet("/liquorestablishmentscreate")
public class LiquorEstablishmentsCreate extends HttpServlet {
	protected LiquorEstablishmentsDao liquorEstablishmentsDao;
	
	@Override
	public void init() throws ServletException {
		liquorEstablishmentsDao = LiquorEstablishmentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages); 
        req.getRequestDispatcher("/LiquorEstablishmentsCreate.jsp").forward(req, resp);
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
        	String comments = req.getParameter("comments");
        	String timeClose = req.getParameter("timeclose");
        	String patronsOff = req.getParameter("patronsoff");
        	String capacityString = req.getParameter("capacity");
	        try {
	        	int establishmentKey = Integer.parseInt(establishmentKeyString);
	        	int capacity = Integer.parseInt(capacityString);
	        	LiquorEstablishments LE = new LiquorEstablishments(establishmentKey, comments, timeClose, patronsOff, capacity);
	        	LE = liquorEstablishmentsDao.create(LE);
	        	messages.put("success", "Successfully created Liquor Establishment for Establishment Key " + establishmentKey);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LiquorEstablishmentsCreate.jsp").forward(req, resp);
    }
}