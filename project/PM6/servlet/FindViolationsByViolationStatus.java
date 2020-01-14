package food.servlet;

import food.dal.*;
import food.model.*;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/findviolationsbyviolationstatus")
public class FindViolationsByViolationStatus extends HttpServlet {
	
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

        List<ViolationHistory> violationHistory = new ArrayList<ViolationHistory>();
        
        // Retrieve and validate status.
        // status is retrieved from the URL query string.
        String status = req.getParameter("violationstatus");
        if (status == null || status.trim().isEmpty()) {
            messages.put("success", "Please enter a status.");
        } else {
        	// Retrieve ViolationHistory, and store as a message.
        	try {
        		violationHistory = violationHistoryDao.getViolationHistoryByVStatus(status);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + status);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindViolationsByViolationStatus.jsp.
        	messages.put("previousViolationStatus", status);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationsByViolationStatus.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<ViolationHistory> violationHistory = new ArrayList<ViolationHistory>();
        
        // Retrieve and validate status.
        // status is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindViolationsByViolationStatus.jsp).
        String status = req.getParameter("violationstatus");
        if (status == null || status.trim().isEmpty()) {
            messages.put("success", "Please enter a valid status.");
        } else {
        	// Retrieve violations, and store as a message.
        	try {
        		violationHistory = violationHistoryDao.getViolationHistoryByVStatus(status);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + status);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationsByViolationStatus.jsp").forward(req, resp);
    }
}
