package food.dal;

import food.model.*;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class FoodEstablishmentsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static FoodEstablishmentsDao instance = null;
	protected FoodEstablishmentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodEstablishmentsDao getInstance() {
		if(instance == null) {
			instance = new FoodEstablishmentsDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public FoodEstablishments create(FoodEstablishments foodEstablishment) throws SQLException {
		String insertFoodEstablishment = "INSERT INTO FoodEstablishments(FoodEstablishmentKey, EstablishmentFK, EstablishmentName)"
				+ " VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodEstablishment);
	        insertStmt.setInt(1, (foodEstablishment.getFoodEstablishmentKey()));
	        insertStmt.setInt(2, (foodEstablishment.getEstablishmentFK()));
	        insertStmt.setString(3, (foodEstablishment.getEstablishmentName()));
			insertStmt.executeUpdate();
			return foodEstablishment;
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


	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public FoodEstablishments deleteByName(String estabName) throws SQLException {
		String deleteFoodEstablishment = "DELETE FROM FoodEstablishments WHERE EstablishmentName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodEstablishment);
			deleteStmt.setString(1, estabName);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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

	
	public FoodEstablishments getFoodEstablishmentByKey(int foodEstablishmentKey) throws SQLException {
		String selectFoodEstablishment = "SELECT * FROM FoodEstablishments WHERE FoodEstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodEstablishment);
			selectStmt.setInt(1, foodEstablishmentKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int foodEstabKey = results.getInt("FoodEstablishmentKey");
				int estabKey = results.getInt("EstablishmentFK");
				String estabName = results.getString("EstablishmentName");				
				FoodEstablishments foodEstablishment = new FoodEstablishments(foodEstabKey, estabKey, estabName);
				return foodEstablishment;
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
	
	public FoodEstablishments getFoodEstablishmentByEstablishmentKey(int establishmentKey) throws SQLException {
		String selectFoodEstablishment = "SELECT * FROM FoodEstablishments WHERE EstablishmentFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodEstablishment);
			selectStmt.setInt(1, establishmentKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int foodEstabKey = results.getInt("FoodEstablishmentKey");
				int estabKey = results.getInt("EstablishmentFK");
				String estabName = results.getString("EstablishmentName");				
				FoodEstablishments foodEstablishment = new FoodEstablishments(foodEstabKey, estabKey, estabName);
				return foodEstablishment;
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
	
	public FoodEstablishments getFoodEstablishmentByName(String establishmentName) throws SQLException {
		String selectFoodEstablishment = "SELECT * FROM FoodEstablishments WHERE EstablishmentName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodEstablishment);
			selectStmt.setString(1, establishmentName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int foodEstabKey = results.getInt("FoodEstablishmentKey");
				int estabKey = results.getInt("EstablishmentFK");
				String estabName = results.getString("EstablishmentName");				
				FoodEstablishments foodEstablishment = new FoodEstablishments(foodEstabKey, estabKey, estabName);
				return foodEstablishment;
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
	
	
}
