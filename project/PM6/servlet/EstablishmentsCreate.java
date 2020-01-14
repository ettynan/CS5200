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

@WebServlet("/establishmentscreate")
public class EstablishmentsCreate extends HttpServlet {
	protected EstablishmentsDao establishmentsDao;
	
	@Override
	public void init() throws ServletException {
		establishmentsDao = EstablishmentsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages); 
        req.getRequestDispatcher("/EstablishmentsCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate establishment key.
        String establishmentKeyString = req.getParameter("establishmentkey");
        if (establishmentKeyString == null || establishmentKeyString.trim().isEmpty()) {
            messages.put("success", "Invalid EstablishmentKey");
        } else {
        	// Create the establishment.
        	String establishmentName = req.getParameter("establishmentname");
        	String establishmentOwner = req.getParameter("establishmentowner");
        	String licenseFKString = req.getParameter("licensefk");
	        try {
	        	int establishmentKey = Integer.parseInt(establishmentKeyString);
	        	int licenseFK = Integer.parseInt(licenseFKString);
	        	Establishments establishment = new Establishments(establishmentKey, establishmentName, establishmentOwner, licenseFK);
	        	establishment = establishmentsDao.create(establishment);
	        	messages.put("success", "Successfully created " + establishmentName + " with Key = " + establishmentKey);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/EstablishmentsCreate.jsp").forward(req, resp);
    }
}