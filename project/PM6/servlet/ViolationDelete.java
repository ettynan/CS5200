
package food.servlet;

import food.dal.*;
import food.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/violationdelete")
public class ViolationDelete extends HttpServlet {
	
	protected ViolationHistoryDao violationHistoryDao;
	
	@Override
	public void init() throws ServletException {
		violationHistoryDao = ViolationHistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Violation History");        
        req.getRequestDispatcher("/ViolationDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String violationHistoryKeyString = req.getParameter("violationhistorykey");
        if (violationHistoryKeyString == null || violationHistoryKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid Key");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Inspection.
	        try {
	        	int violationHistoryKeyInt = Integer.parseInt(violationHistoryKeyString);
	        	ViolationHistory violationHistory = new ViolationHistory(violationHistoryKeyInt);

	        	violationHistory = violationHistoryDao.delete(violationHistory);
	        	// Update the message.
		        if (violationHistory == null) {
		            messages.put("title", "Successfully deleted " + violationHistoryKeyInt);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + violationHistoryKeyInt);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ViolationDelete.jsp").forward(req, resp);
    }
}
