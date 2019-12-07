
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


@WebServlet("/violationupdate")
public class ViolationUpdate extends HttpServlet {
	
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
        
        // Retrieve key and validate.
        String iSHK = req.getParameter("violationhistorykey");
        if (iSHK == null || iSHK.trim().isEmpty()){
            messages.put("success", "Please enter a valid key.");
        } else {
        	try {
                int violationHistoryKey = Integer.parseInt(iSHK);
                ViolationHistory violationHistory = violationHistoryDao.getByViolationHistoryKey(violationHistoryKey);
        		if(violationHistory == null) {
        			messages.put("success", "Key does not exist.");
        		}
        		req.setAttribute("violationHistory", violationHistory);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ViolationUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve key and validate.
        String iSHK = req.getParameter("violationhistorykey");

        if (iSHK == null || iSHK.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	try {
                int violationHistoryKey = Integer.parseInt(iSHK);
                ViolationHistory violationHistory = violationHistoryDao.getByViolationHistoryKey(violationHistoryKey);
        		if(violationHistory == null) {
        			messages.put("success", "Key does not exist. No update to perform.");
        		} else {
        			String newViolationComments = req.getParameter("violationcomment");
        			if (newViolationComments == null || newViolationComments.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Result.");
        	        } else {
        	        	violationHistory = violationHistoryDao.updateViolationComments(violationHistory, newViolationComments);
        	        	messages.put("success", "Successfully updated " + violationHistoryKey);
        	        }
        		}
        		req.setAttribute("violationHistory", violationHistory);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/InspectionUpdate.jsp").forward(req, resp);
    }
}
