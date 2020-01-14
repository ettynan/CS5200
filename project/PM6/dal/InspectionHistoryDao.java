package food.dal;

import food.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * InspectionHistoryDao class containing methods for InspectionHistory CRUD operations
 *
 */

public class InspectionHistoryDao {
	
	protected ConnectionManager connectionManager;

    private static InspectionHistoryDao instance = null; 

    protected InspectionHistoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static InspectionHistoryDao getInstance() {
        if (instance == null) {
            instance = new InspectionHistoryDao();
        }
        return instance;
    }
    
    // Create
    /**
     * 
     * @param inspectionHistory
     * @return new inspectionHistory
     * @throws SQLException
     */
    public InspectionHistory create(InspectionHistory inspectionHistory) throws SQLException {
        String insertInspectionHistory =
                "INSERT INTO InspectionHistory(EstablishmentName, InspectionDate, InspectionResult, FoodEstablishmentFK) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertInspectionHistory, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, (inspectionHistory.getEstablishmentName()));
            insertStmt.setTimestamp(2, new Timestamp(inspectionHistory.getInspectionDate().getTime()));
            insertStmt.setString(3, (inspectionHistory.getInspectionResult()));
            insertStmt.setInt(4, (inspectionHistory.getFoodEstablishment().getFoodEstablishmentKey()));
            insertStmt.executeUpdate();
         // Retrieve the auto-generated key and set it, so it can be used by the caller.
         	resultKey = insertStmt.getGeneratedKeys();
            int inspectionHistoryKey = -1;
            if(resultKey.next()) {
            	inspectionHistoryKey = resultKey.getInt(1);
            } else {
				throw new SQLException("Unable to retrieve auto-generated key.");
            }
            inspectionHistory.setInspectionHistoryKey(inspectionHistoryKey);
            return inspectionHistory;
             
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
     * @param inspectionHistoryKey
     * @return inspectionHistory with that IHKey
     * @throws SQLException
     */
    public InspectionHistory getInspectionHistoryByISHKey(int inspectionHistoryKey) throws SQLException {
        String selectInspectionHistory = "SELECT * "
                + "FROM InspectionHistory WHERE InspectionHistoryKey=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInspectionHistory);
            selectStmt.setInt(1, inspectionHistoryKey);
            results = selectStmt.executeQuery();
			FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
            if(results.next()) {
            	int inspHisKey = results.getInt("InspectionHistoryKey");
                String estName = results.getString("EstablishmentName");
                Date inspectionDate = results.getDate("InspectionDate");
                String inspectionResult = results.getString("InspectionResult");
				int foodEstabFK = results.getInt("FoodEstablishmentFK");
                
                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                
                InspectionHistory inspectionHistory = new InspectionHistory(inspHisKey, estName, inspectionDate, inspectionResult, foodEstablishmentFK);
                return inspectionHistory;
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
     * @return list of inspectionHistories with that FEKey
     * @throws SQLException
     */
    public List<InspectionHistory> getInspectionHistoryByFEKey(int foodEstablishmentKey) throws SQLException {
    	List<InspectionHistory> inspectionHistories = new ArrayList<InspectionHistory>();
    	String selectInspectionHistory = "SELECT * "
                + "FROM InspectionHistory WHERE FoodEstablishmentFK=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
		FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInspectionHistory);
            selectStmt.setInt(1, foodEstablishmentKey);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int inspHisKey = results.getInt("InspectionHistoryKey");
                String estName = results.getString("EstablishmentName");
                Date inspectionDate = results.getDate("InspectionDate");
                String inspectionResult = results.getString("InspectionResult");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                                
                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistory = new InspectionHistory(inspHisKey, estName, inspectionDate, inspectionResult, foodEstablishmentFK);
                inspectionHistories.add(inspectionHistory);
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
        return inspectionHistories;
    }
    
    // Read
    /**
     * 
     * @param establishmentName
     * @return list of inspectionHistories with that estName
     * @throws SQLException
     */
    public List<InspectionHistory> getInspectionHistoryByEstablishmentName(String establishmentName) throws SQLException {
    	List<InspectionHistory> inspectionHistories = new ArrayList<InspectionHistory>();
    	String selectInspectionHistory = "SELECT * "
                + "FROM InspectionHistory WHERE EstablishmentName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;		
        FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInspectionHistory);
            selectStmt.setString(1, establishmentName);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int inspHisKey = results.getInt("InspectionHistoryKey");
                String estName2 = results.getString("EstablishmentName");
                Date inspectionDate = results.getDate("InspectionDate");
                String inspectionResult = results.getString("InspectionResult");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                
                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistory = new InspectionHistory(inspHisKey, estName2, inspectionDate, inspectionResult, foodEstablishmentFK);
                inspectionHistories.add(inspectionHistory);
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
        return inspectionHistories;
    }
    
    // Read
    /**
     * 
     * @param inspectionDate
     * @return list of inspectionHistories with that inspDate
     * @throws SQLException
     */
    public List<InspectionHistory> getInspectionHistoryByInspectionDate(Date inspectionDate) throws SQLException {
    	List<InspectionHistory> inspectionHistories = new ArrayList<InspectionHistory>();
    	String selectInspectionHistory = "SELECT * "
                + "FROM InspectionHistory WHERE InspectionDate=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInspectionHistory);
			selectStmt.setTimestamp(1, new Timestamp(inspectionDate.getTime()));
            results = selectStmt.executeQuery();
            while(results.next()) {
                int inspectionHistoryKey = results.getInt("InspectionHistoryKey");
                String estName = results.getString("EstablishmentName");
                String inspectionResult = results.getString("InspectionResult");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                
                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistory = new InspectionHistory(inspectionHistoryKey, estName, inspectionDate, inspectionResult, foodEstablishmentFK);
                inspectionHistories.add(inspectionHistory);
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
        return inspectionHistories;
    }
    
    // Read
    /**
     * 
     * @param inspectionResult
     * @return list of inspectionHistories with that inspResult
     * @throws SQLException
     */
    public List<InspectionHistory> getInspectionHistoryByInspectionResult(String inspectionResult) throws SQLException {
    	List<InspectionHistory> inspectionHistories = new ArrayList<InspectionHistory>();
    	String selectInspectionHistory = "SELECT * "
                + "FROM InspectionHistory WHERE InspectionResult=? LIMIT 100;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        FoodEstablishmentsDao foodEstablishmentsDao = FoodEstablishmentsDao.getInstance();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInspectionHistory);
            selectStmt.setString(1, inspectionResult);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int inspectionHistoryKey = results.getInt("InspectionHistoryKey");
                String estName = results.getString("EstablishmentName");
                Date inspDate = results.getDate("InspectionDate");
                String inspectionResult2 = results.getString("InspectionResult");
                int foodEstabFK = results.getInt("FoodEstablishmentFK");
                
                FoodEstablishments foodEstablishmentFK = foodEstablishmentsDao.getFoodEstablishmentByKey(foodEstabFK);
                InspectionHistory inspectionHistory = new InspectionHistory(inspectionHistoryKey, estName, inspDate, inspectionResult2, foodEstablishmentFK);
                inspectionHistories.add(inspectionHistory);
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
        return inspectionHistories;
    }
    
    //Update
    /**
     * 
     * @param inspectionHistory
     * @param newInspectionResult
     * @return inspectionHistory with updated InspectionResult
     * @throws SQLException
     */
    public InspectionHistory updateInspectionResult(InspectionHistory inspectionHistory, String newInspectionResult) throws SQLException {
        String updateInspectionHistory = "UPDATE InspectionHistory SET InspectionResult=? WHERE InspectionHistoryKey=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateInspectionHistory);
            updateStmt.setString(1, newInspectionResult);
            updateStmt.setInt(2, inspectionHistory.getInspectionHistoryKey());
            updateStmt.executeUpdate();

            // Update the about param before returning to the caller.
            inspectionHistory.setInspectionResult(newInspectionResult);
            return inspectionHistory;
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

    // Delete
    /**
     * 
     * @param inspectionHistory
     * @return removes the inspectionHistory with that estName
     * @throws SQLException
     */
    public InspectionHistory delete(InspectionHistory inspectionHistory) throws SQLException {
        String deleteInspectionHistory = "DELETE FROM InspectionHistory WHERE InspectionHistoryKey=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteInspectionHistory);
            deleteStmt.setInt(1, inspectionHistory.getInspectionHistoryKey());
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
