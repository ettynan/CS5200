package food.dal;

import food.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with {@link FoodLicenses} model for CRUD operations.
 * @author Clara Mae Wells
 */
public class FoodLicensesDao {
	// Singleton pattern to limit instantiation to one object.
	protected ConnectionManager connectionManager;
	
	private static FoodLicensesDao instance = null;
	
	protected FoodLicensesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static FoodLicensesDao getInstance() {
		if(instance == null) {
			instance = new FoodLicensesDao();
		}
		return instance;
	}
	
	/**
	 * Create a new FoodLicenses instance. 
	 * Runs an INSERT statement.
	 */
	public FoodLicenses create(FoodLicenses foodLicense) throws SQLException {
		String insertFoodLicense =
			"INSERT INTO FoodLicenses(FoodLicenseKey,LicenseFK,LicenseNumber) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFoodLicense);
			insertStmt.setInt(1, foodLicense.getFoodLicenseKey());
			insertStmt.setInt(2, foodLicense.getLicenseFK());
			insertStmt.setString(3, foodLicense.getLicenseNumber());
			insertStmt.executeUpdate();
			return foodLicense;
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
	 * Get FoodLicenses record by foodLicenseKey.
	 * Runs a SELECT statement and returns a single FoodLicenses instance.
	 */
	public FoodLicenses getFoodLicenseByFoodLicenseKey(int foodLicenseKey) throws SQLException {
		String selectFoodLicense = "SELECT FoodLicenseKey,LicenseFK,LicenseNumber "
				+ "FROM FoodLicenses "
				+ "WHERE FoodLicenseKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodLicense);
			selectStmt.setInt(1, foodLicenseKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int licenseFK = results.getInt("LicenseFK");
				String licenseNumber = results.getString("LicenseNumber");
			
				FoodLicenses foodLicense = new FoodLicenses(foodLicenseKey, licenseFK, licenseNumber);
				return foodLicense;
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
	
	/**
	 * Get FoodLicenses record by licenseFK.
	 * Runs a SELECT statement and returns a single FoodLicenses instance.
	 */
	public FoodLicenses getFoodLicenseByLicenseFK(int licenseFK) throws SQLException {
		String selectFoodLicense = "SELECT FoodLicenseKey,LicenseFK,LicenseNumber "
				+ "FROM FoodLicenses "
				+ "WHERE LicenseFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodLicense);
			selectStmt.setInt(1, licenseFK);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int foodLicenseKey = results.getInt("FoodLicenseKey");
				String licenseNumber = results.getString("LicenseNumber");
				
				FoodLicenses foodLicense = new FoodLicenses(foodLicenseKey, licenseFK, licenseNumber);
				return foodLicense;
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
	
	/**
	 * Get all FoodLicenses records by foodLicenseNumber.
	 * Runs a SELECT statement and returns a list of FoodLicenses that have the specified foodLicenseNumber.
	 */
	public List<FoodLicenses> getFoodLicensesByLicenseNumber(String licenseNumber) throws SQLException {
		List<FoodLicenses> foodLicenses = new ArrayList<FoodLicenses>();
		String selectFoodLicenses = "SELECT FoodLicenseKey,LicenseFK,LicenseNumber "
				+ "FROM FoodLicenses "
				+ "WHERE LicenseNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodLicenses);
			selectStmt.setString(1, licenseNumber);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int foodLicenseKey = results.getInt("FoodLicenseKey");
				int licenseFK = results.getInt("LicenseFK");
				licenseNumber = results.getString("LicenseNumber");
				
				FoodLicenses foodLicense = new FoodLicenses(foodLicenseKey, licenseFK, licenseNumber);
				foodLicenses.add(foodLicense);
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
		return foodLicenses;
	}
	
	/**
	 * Delete a FoodLicenses instance.
	 * Runs a DELETE statement.
	 */
	public FoodLicenses delete(FoodLicenses foodLicense) throws SQLException {
		String deleteFoodLicense = "DELETE FROM FoodLicenses WHERE FoodLicenseKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodLicense);
			deleteStmt.setInt(1, foodLicense.getFoodLicenseKey());
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