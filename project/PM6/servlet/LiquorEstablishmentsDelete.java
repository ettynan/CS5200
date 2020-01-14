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

@WebServlet("/liquorestablishmentsdelete")
public class LiquorEstablishmentsDelete extends HttpServlet {
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
        messages.put("title", "Delete Liquor Establishment");        
        req.getRequestDispatcher("/LiquorEstablishmentsDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate establishment key.
        String liquorEstabKeyString = req.getParameter("liquorestabkey");
        if (liquorEstabKeyString == null || liquorEstabKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid Liquor Establishment Key");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Establishment.   
	        try {
	        	int liquorEstabKey = Integer.parseInt(liquorEstabKeyString);
		        LiquorEstablishments LE = new LiquorEstablishments(liquorEstabKey);	    
	        	LE = liquorEstablishmentsDao.deleteByKey(liquorEstabKey);
	        	// Update the message.
		        if (LE == null) {
		            messages.put("title", "Successfully deleted Liquor Establishment Key" + liquorEstabKey);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete Liquor Establishment Key" + liquorEstabKey);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/LiquorEstablishmentsDelete.jsp").forward(req, resp);
    }
}