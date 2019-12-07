package food.dal;

import food.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interacts with {@link LiquorLicenses} model for CRUD operations.
 * @author Clara Mae Wells
 */
public class LiquorLicensesDao {
	// Singleton pattern to limit instantiation to one object.
	protected ConnectionManager connectionManager;
	
	private static LiquorLicensesDao instance = null;
	
	protected LiquorLicensesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LiquorLicensesDao getInstance() {
		if(instance == null) {
			instance = new LiquorLicensesDao();
		}
		return instance;
	}
	
	/**
	 * Create a new LiquorLicenses instance. 
	 * Runs an INSERT statement.
	 */
	public LiquorLicenses create(LiquorLicenses liquorLicense) throws SQLException {
		String insertLiquorLicense =
			"INSERT INTO LiquorLicenses(LiquorLicenseKey,LiquorLicenseComments,LiquorLocationComments,LicenseFK,LicenseNumber) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLiquorLicense);
			insertStmt.setInt(1, liquorLicense.getLiquorLicenseKey());
			insertStmt.setString(2, liquorLicense.getLiquorLicenseComments());
			insertStmt.setString(3, liquorLicense.getLiquorLocationComments());
			insertStmt.setInt(4, liquorLicense.getLicenseFK());
			insertStmt.setString(5, liquorLicense.getLicenseNumber());
			insertStmt.executeUpdate();
			return liquorLicense;
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
	 * Get LiquorLicenses record by liquorLicenseKey.
	 * Runs a SELECT statement and returns a single LiquorLicenses instance.
	 */
	public LiquorLicenses getLiquorLicenseByLiquorLicenseKey(int liquorLicenseKey) throws SQLException {
		String selectLiquorLicense = "SELECT LiquorLicenseKey,LiquorLicenseComments,LiquorLocationComments,LicenseFK,LicenseNumber "
				+ "FROM LiquorLicenses "
				+ "WHERE LiquorLicenseKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLiquorLicense);
			selectStmt.setInt(1, liquorLicenseKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String liquorLicenseComments = results.getString("LiquorLicenseComments");
				String liquorLocationComments = results.getString("LiquorLocationComments");
				int licenseFK = results.getInt("LicenseFK");
				String licenseNumber = results.getString("LicenseNumber");
				
				LiquorLicenses liquorLicense = new LiquorLicenses(liquorLicenseKey, liquorLicenseComments, liquorLocationComments, licenseFK, licenseNumber);
				return liquorLicense;
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
	 * Get LiquorLicenses record by licenseFK.
	 * Runs a SELECT statement and returns a single LiquorLicenses instance.
	 */
	public LiquorLicenses getLiquorLicenseByLicenseFK(int licenseFK) throws SQLException {
		String selectLiquorLicense = "SELECT LiquorLicenseKey,LiquorLicenseComments,LiquorLocationComments,LicenseFK,LicenseNumber "
				+ "FROM LiquorLicenses "
				+ "WHERE LicenseFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLiquorLicense);
			selectStmt.setInt(1, licenseFK);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int liquorLicenseKey = results.getInt("LiquorLicenseKey");
				String liquorLicenseComments = results.getString("LiquorLicenseComments");
				String liquorLocationComments = results.getString("LiquorLocationComments");
				String licenseNumber = results.getString("LicenseNumber");
				
				LiquorLicenses liquorLicense = new LiquorLicenses(liquorLicenseKey, liquorLicenseComments, liquorLocationComments, licenseFK, licenseNumber);
				return liquorLicense;
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
	 * Get all LiquorLicenses records by liquorLicenseNumber.
	 * Runs a SELECT statement and returns a list of LiquorLicenses that have the specified liquorLicenseNumber.
	 */
	public List<LiquorLicenses> getLiquorLicensesByLicenseNumber(String licenseNumber) throws SQLException {
		List<LiquorLicenses> liquorLicenses = new ArrayList<LiquorLicenses>();
		String selectLiquorLicenses = "SELECT LiquorLicenseKey,LiquorLicenseComments,LiquorLocationComments,LicenseFK,LicenseNumber "
				+ "FROM LiquorLicenses "
				+ "WHERE LicenseNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLiquorLicenses);
			selectStmt.setString(1, licenseNumber);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int liquorLicenseKey = results.getInt("LiquorLicenseKey");
				String liquorLicenseComments = results.getString("LiquorLicenseComments");
				String liquorLocationComments = results.getString("LiquorLocationComments");
				int licenseFK = results.getInt("LicenseFK");
				licenseNumber = results.getString("LicenseNumber");
				
				LiquorLicenses liquorLicense = new LiquorLicenses(liquorLicenseKey, liquorLicenseComments, liquorLocationComments, licenseFK, licenseNumber);
				liquorLicenses.add(liquorLicense);
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
		return liquorLicenses;
	}
	
	/**
	 * Delete a LiquorLicenses instance.
	 * Runs a DELETE statement.
	 */
	public LiquorLicenses delete(LiquorLicenses liquorLicense) throws SQLException {
		String deleteLiquorLicense = "DELETE FROM LiquorLicenses WHERE LiquorLicenseKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLiquorLicense);
			deleteStmt.setInt(1, liquorLicense.getLiquorLicenseKey());
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