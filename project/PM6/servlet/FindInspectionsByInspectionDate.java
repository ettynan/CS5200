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


@WebServlet("/findinspectionsbyinspectiondate")
public class FindInspectionsByInspectionDate extends HttpServlet {
protected InspectionHistoryDao inspectionHistoryDao;
	
	@Override
	public void init() throws ServletException {
		inspectionHistoryDao = InspectionHistoryDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<InspectionHistory> inspectionHistory = new ArrayList<InspectionHistory>();
        
        // Retrieve and validate inspectionDate
        String inspectionDateString = req.getParameter("inspectiondate");
        if (inspectionDateString == null || inspectionDateString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid InspectionDate.");
        } else {
        	// Retrieve Inspections and store as a message.
        	// Dates must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date inspectionDate = new Date();
        	try {
        		inspectionDate = dateFormat.parse(inspectionDateString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	try {
            	inspectionHistory = inspectionHistoryDao.getInspectionHistoryByInspectionDate(inspectionDate);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + inspectionDateString);
        	messages.put("previousInspectionDate", inspectionDateString);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionsByInspectionDate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<InspectionHistory> inspectionHistory = new ArrayList<InspectionHistory>();
        
        // Retrieve and validate inspectionDate.
        String inspectionDateString = req.getParameter("inspectiondate");
        if (inspectionDateString == null || inspectionDateString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid InspectionDate.");
        } else {
        	// Retrieve Inspections and store as a message.
        	// Dates must be in the format yyyy-mm-dd.
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	Date inspectionDate = new Date();
        	try {
        		inspectionDate = dateFormat.parse(inspectionDateString);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
        	try {
            	inspectionHistory = inspectionHistoryDao.getInspectionHistoryByInspectionDate(inspectionDate);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying result for " + inspectionDateString);
        }
        req.setAttribute("inspectionHistory", inspectionHistory);
        
        req.getRequestDispatcher("/FindInspectionsByInspectionDate.jsp").forward(req, resp);
    }
}