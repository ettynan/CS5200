package food.dal;
import food.model.*;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ViolationHistoryDao class containing methods for ViolationHistory CRUD operations
 *
 */
public class ViolationHistoryDao {
	
	protected ConnectionManager connectionManager;

    private static ViolationHistoryDao instance = null;

    protected ViolationHistoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static ViolationHistoryDao getInstance() {
        if (instance == null) {
            instance = new ViolationHistoryDao();
        }
        return instance;
    }
    //Create
    /**
     * 
     * @param violationHistory
     * @return A new violationHistory 
     * @throws SQLException
     */
    public ViolationHistory create(ViolationHistory violationHistory) throws SQLException {
        String insertViolationHistory =
                "INSERT INTO ViolationHistory(EstablishmentName, ViolationCode, ViolationLevel, ViolationDescription, ViolationDate,"
                + "ViolationStatus, ViolationComments, FoodEstablishmentFK, "
                + "InspectionHistoryFK) " +
                        "VALUES(?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertViolationHistory);
            insertStmt = connection.prepareStatement(insertViolationHistory, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, (violationHistory.getEstablishmentName()));
            insertStmt.setString(2, (violationHistory.getViolationCode()));
            insertStmt.setString(3, (violationHistory.getViolationLevel()));
            insertStmt.setString(4, (violationHistory.getViolationDescription()));
            insertStmt.setTimestamp(5, new Timestamp(violationHistory.getViolationDate().getTime()));
            insertStmt.setString(6, (violationHistory.getViolationStatus()));
            insertStmt.setString(7, (violationHistory.getViolationComments()));
            insertStmt.setInt(8, (violationHistory.getFoodEstablishment().getFoodEstablishmentKey()));
            insertStmt.setInt(9, (violationHistory.getInspectionHistory().getInspectionHistoryKey()));
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int violationHistoryKey = -1;
            if(resultKey.next()) {
            	violationHistoryKey = resultKey.getInt(1);
            } else {
				throw new SQLException("Unable to retrieve auto-generated key.");
            }
            violationHistory.setViolationHistoryKey(violationHistoryKey);
            return violationHistory;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
        }
    }
    
    // Read
    /**
     * 
     * @param violationHistoryKey
     * @return violationHistory with that violHisKey
     * @throws SQLException
     */
    public ViolationHistory getByViolationHistoryKey(int violationHistoryKey) throws SQLException {
        String selectViolationHistory = "SELECT * "
        		 + "FROM ViolationHistory WHERE ViolationHistoryKey=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setInt(1, violationHistoryKey);
            results = selectStmt.executeQuery();
			FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();

            if(results.next()) {
                int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel, vioDesc, violationDate, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                return violationHistory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    // Read
    /**
     * 
     * @param foodEstablishmentKey
     * @return violationHistory with that foodEstKey
     * @throws SQLException
     */
    public ViolationHistory getViolationHistoryByFoodEstFK(int foodEstablishmentKey) throws SQLException {
        String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE FoodEstablishmentFK=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setInt(1, foodEstablishmentKey);
            results = selectStmt.executeQuery();
            FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
            if(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel, vioDesc, violationDate, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                return violationHistory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    // Read
    /**
     * 
     * @param inspectionHistoryFK
     * @return violationHistory with that inspHisKey
     * @throws SQLException
     */
    public ViolationHistory getViolationHistoryByInspHisFK(int inspectionHistoryKey) throws SQLException {
        String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE InspectionHistoryFK=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setInt(1, inspectionHistoryKey);
            results = selectStmt.executeQuery();
            FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
            if(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel, vioDesc, violationDate, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                return violationHistory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    // Read
    /**
     * 
     * @param violationCode
     * @return list of vilationHistories with that violationCode
     * @throws SQLException
     */
    public List<ViolationHistory> getViolationHistoryByVCode(String violationCode) throws SQLException {
    	List<ViolationHistory> violationHistories = new ArrayList<ViolationHistory>();
    	String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE ViolationCode=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setString(1, violationCode);
            results = selectStmt.executeQuery();
            FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
            if(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code2 = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code2, violationLevel, vioDesc, violationDate, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                violationHistories.add(violationHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return violationHistories;
    }
    
    // Read
    /**
     * 
     * @param violationLevel
     * @return list of vilationHistories with that violationLevel
     * @throws SQLException
     */
    public List<ViolationHistory> getViolationHistoryByVLevel(String violationLevel) throws SQLException {
    	List<ViolationHistory> violationHistories = new ArrayList<ViolationHistory>();
    	String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE ViolationLevel=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setString(1, violationLevel);
            results = selectStmt.executeQuery();
            FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
            while(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel1 = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel1, vioDesc, violationDate, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                violationHistories.add(violationHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return violationHistories;
    }
    
 // Read
    /**
     * 
     * @param establishmentName
     * @return list of violationHistories with that estName
     * @throws SQLException
     */
    public List<ViolationHistory> getViolationHistoryByEstablishmentName(String establishmentName) throws SQLException {
    	List<ViolationHistory> violationHistories = new ArrayList<ViolationHistory>();
    	String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE EstablishmentName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;		
        FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
		InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setString(1, establishmentName);
            results = selectStmt.executeQuery();
            while(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate2 = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");
                
                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel, vioDesc, violationDate2, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                violationHistories.add(violationHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return violationHistories;
    }
    
   
    
    // Read
    /**
     * 
     * @param violationDate
     * @return list of vilationHistories with that violationDate
     * @throws SQLException
     */
    public List<ViolationHistory> getViolationHistoryByVDate(Date violationDate) throws SQLException {
    	List<ViolationHistory> violationHistories = new ArrayList<ViolationHistory>();
    	String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE ViolationDate=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setDate(1, violationDate);
            results = selectStmt.executeQuery();
            FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
            while(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate2 = results.getDate("ViolationDate");
                String violationStatus = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel, vioDesc, violationDate2, violationStatus, comments, foodEstablishmentFK, inspectionHistoryFK);
                violationHistories.add(violationHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return violationHistories;
    }
    
    // Read
    /**
     * 
     * @param violationStatus
     * @return list of vilationHistories with that violationStatus
     * @throws SQLException
     */
    public List<ViolationHistory> getViolationHistoryByVStatus(String violationStatus) throws SQLException {
    	List<ViolationHistory> violationHistories = new ArrayList<ViolationHistory>();
    	String selectViolationHistory = "SELECT * "
                + "FROM ViolationHistory WHERE ViolationStatus=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectViolationHistory);
            selectStmt.setString(1, violationStatus);
            results = selectStmt.executeQuery();
            FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
			InspectionHistoryDao inspectionHistoryDao = InspectionHistoryDao.getInstance();
            while(results.next()) {
            	int violHisKey = results.getInt("ViolationHistoryKey");
                String estName = results.getString("EstablishmentName");
                String code = results.getString("ViolationCode");
                String violationLevel = results.getString("ViolationLevel");
                String vioDesc = results.getString("ViolationDescription");
                Date violationDate = results.getDate("ViolationDate");
                String violationStatus1 = results.getString("ViolationStatus");
                String comments = results.getString("ViolationComments");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                int inspHistoryFK = results.getInt("InspectionHistoryFK");

                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistoryFK = inspectionHistoryDao.getInspectionHistoryByISHKey(inspHistoryFK);
                ViolationHistory violationHistory = new ViolationHistory(violHisKey, estName, code, violationLevel, vioDesc, violationDate, violationStatus1, comments, foodEstablishmentFK, inspectionHistoryFK);
                violationHistories.add(violationHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return violationHistories;
    }
    
    // Update
    /**
     * 
     * @param violationHistory
     * @param newViolationComments
     * @return violationHistory with updated ViolationComments
     * @throws SQLException
     */
    public ViolationHistory updateViolationComments(ViolationHistory violationHistory, String newViolationComments) throws SQLException {
        String updateViolationHistory = "UPDATE ViolationHistory SET ViolationComments=? WHERE ViolationHistoryKey=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateViolationHistory);
            updateStmt.setString(1, newViolationComments);
            updateStmt.setInt(2, violationHistory.getViolationHistoryKey());
            updateStmt.executeUpdate();

            // Update the about param before returning to the caller.
            violationHistory.setViolationComments(newViolationComments);
            return violationHistory;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(updateStmt != null) {
                updateStmt.close();
            }
        }
    } 
    
    //Delete
    /**
     * 
     * @param violationHistory
     * @return removes violationHistory with that estName
     * @throws SQLException
     */
    public ViolationHistory delete(ViolationHistory violationHistory) throws SQLException {
        String deleteInspectionHistory = "DELETE FROM ViolationHistory WHERE ViolationHistoryKey=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteInspectionHistory);
            deleteStmt.setInt(1, violationHistory.getViolationHistoryKey());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

}
