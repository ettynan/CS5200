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


@WebServlet("/findviolationsbyviolationdate")
public class FindViolationsByViolationDate extends HttpServlet {
protected ViolationHistoryDao violationHistoryDao;
	
	@Override
	public void init() throws ServletException {
		violationHistoryDao = ViolationHistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<ViolationHistory> violationHistory = new ArrayList<ViolationHistory>();
        
        // Retrieve and validate violationDate
        String violationDateString = req.getParameter("violationdate");
        if (violationDateString == null || violationDateString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ViolationDate.");
        } else {
        	// Retrieve Violations and store as a message.
        	// Dates must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date violationDate = new Date();
        	try {
        		violationDate = dateFormat.parse(violationDateString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	try {
        		violationHistory = violationHistoryDao.getViolationHistoryByVDate(violationDate);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + violationDateString);
        	messages.put("previousViolationDate", violationDateString);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationsByViolationDate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<ViolationHistory> violationHistory = new ArrayList<ViolationHistory>();
        
        // Retrieve and validate violationDate.
        String violationDateString = req.getParameter("violationdate");
        if (violationDateString == null || violationDateString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ViolationDate.");
        } else {
        	// Retrieve Violations and store as a message.
        	// Dates must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date violationDate = new Date();
        	try {
        		violationDate = dateFormat.parse(violationDateString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	try {
        		violationHistory = violationHistoryDao.getViolationHistoryByVDate(violationDate);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + violationDateString);
        }
        req.setAttribute("violationHistory", violationHistory);
        
        req.getRequestDispatcher("/FindViolationsByViolationDate.jsp").forward(req, resp);
    }
}