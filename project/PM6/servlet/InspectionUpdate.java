
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


@WebServlet("/inspectionupdate")
public class InspectionUpdate extends HttpServlet {
	
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
        
        // Retrieve key and validate.
        String iSHK = req.getParameter("inspectionhistorykey");
        if (iSHK == null || iSHK.trim().isEmpty()){
            messages.put("success", "Please enter a valid key.");
        } else {
        	try {
                int inspectionHistoryKey = Integer.parseInt(iSHK);
        		InspectionHistory inspectionHistory = inspectionHistoryDao.getInspectionHistoryByISHKey(inspectionHistoryKey);
        		if(inspectionHistory == null) {
        			messages.put("success", "Key does not exist.");
        		}
        		req.setAttribute("inspectionHistory", inspectionHistory);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/InspectionUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve key and validate.
        String iSHK = req.getParameter("inspectionhistorykey");

        if (iSHK == null || iSHK.trim().isEmpty()) {
            messages.put("success", "Please enter a valid key.");
        } else {
        	try {
                int inspectionHistoryKey = Integer.parseInt(iSHK);
        		InspectionHistory inspectionHistory = inspectionHistoryDao.getInspectionHistoryByISHKey(inspectionHistoryKey);
        		if(inspectionHistory == null) {
        			messages.put("success", "Key does not exist. No update to perform.");
        		} else {
        			String newInspectionResult = req.getParameter("inspectionresult");
        			if (newInspectionResult == null || newInspectionResult.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Result.");
        	        } else {
        	        	inspectionHistory = inspectionHistoryDao.updateInspectionResult(inspectionHistory, newInspectionResult);
        	        	messages.put("success", "Successfully updated " + inspectionHistoryKey);
        	        }
        		}
        		req.setAttribute("inspectionHistory", inspectionHistory);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/InspectionUpdate.jsp").forward(req, resp);
    }
}
