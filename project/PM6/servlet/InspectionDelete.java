
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

@WebServlet("/inspectiondelete")
public class InspectionDelete extends HttpServlet {
	
	protected InspectionHistoryDao inspectionHistoryDao;
	
	@Override
	public void init() throws ServletException {
		inspectionHistoryDao = InspectionHistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Inspection");        
        req.getRequestDispatcher("/InspectionDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate key.
        String inspectionHistoryKeyString = req.getParameter("inspectionhistorykey");
        if (inspectionHistoryKeyString == null || inspectionHistoryKeyString.trim().isEmpty()) {
            messages.put("title", "Invalid Key");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Inspection.
        	int inspectionHistoryKeyInt = Integer.parseInt(inspectionHistoryKeyString);
        	InspectionHistory inspectionHistory = new InspectionHistory(inspectionHistoryKeyInt);
	        try {
	        	inspectionHistory = inspectionHistoryDao.delete(inspectionHistory);
	        	// Update the message.
		        if (inspectionHistory == null) {
		            messages.put("title", "Successfully deleted " + inspectionHistoryKeyInt);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + inspectionHistoryKeyInt);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/InspectionDelete.jsp").forward(req, resp);
    }
}
