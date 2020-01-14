package food.dal;

import food.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interacts with {@link Licenses} model for CRUD operations.
 * @author Clara Mae Wells
 */
public class LicensesDao {
	// Singleton pattern to limit instantiation to one object.
	protected ConnectionManager connectionManager;
	
	private static LicensesDao instance = null;
	
	protected LicensesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LicensesDao getInstance() {
		if(instance == null) {
			instance = new LicensesDao();
		}
		return instance;
	}
	
	/**
	 * Create a new Licenses instance. 
	 * Runs an INSERT statement.
	 */
	public Licenses create(Licenses license) throws SQLException {
		String insertLicense =
			"INSERT INTO Licenses(LicenseKey,LicenseNumber,LicenseIssue,LicenseExpiration,LicenseStatus,LicenseCategoryFK) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLicense);
			insertStmt.setInt(1, license.getLicenseKey());
			insertStmt.setString(2, license.getLicenseNumber());
			insertStmt.setTimestamp(3, new Timestamp(license.getLicenseIssue().getTime()));
			insertStmt.setTimestamp(4, new Timestamp(license.getLicenseExpiration().getTime()));
			insertStmt.setString(5, license.getLicenseStatus().name());
			insertStmt.setString(6, license.getLicenseCategoryFK());
			insertStmt.executeUpdate();
			return license;
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
	 * Get Licenses record by licenseKey.
	 * Runs a SELECT statement and returns a single Licenses instance.
	 */
	public Licenses getLicenseByLicenseKey(int licenseKey) throws SQLException {
		String selectLicense = "SELECT LicenseKey,LicenseNumber,LicenseIssue,LicenseExpiration,LicenseStatus,LicenseCategoryFK "
				+ "FROM Licenses "
				+ "WHERE LicenseKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLicense);
			selectStmt.setInt(1, licenseKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String licenseNumber = results.getString("LicenseNumber");
				Date licenseIssue = new Date(results.getTimestamp("LicenseIssue").getTime());
				Date licenseExpiration = new Date(results.getTimestamp("LicenseExpiration").getTime());
				Licenses.LicenseStatus licenseStatus = Licenses.LicenseStatus.valueOf(results.getString("LicenseStatus"));
				String licenseCategoryFK = results.getString("LicenseCategoryFK");
				
				Licenses license = new Licenses(licenseKey, licenseNumber, licenseIssue, licenseExpiration, licenseStatus, licenseCategoryFK);
				return license;
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
	 * Get all Licenses records by licenseCategoryFK.
	 * Runs a SELECT statement and returns a list of Licenses that are of the specified licenseCategoryFK.
	 */
	public List<Licenses> getLicensesByLicenseCategoryFK(String licenseCategoryFK) throws SQLException {
		List<Licenses> licenses = new ArrayList<Licenses>();
		String selectLicenses = "SELECT LicenseKey,LicenseNumber,LicenseIssue,LicenseExpiration,LicenseStatus,LicenseCategoryFK "
					+ "FROM Licenses "
					+ "WHERE LicenseCategoryFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLicenses);
			selectStmt.setString(1, licenseCategoryFK);
			results = selectStmt.executeQuery();
			while(results.next()) {				
				int licenseKey = results.getInt("LicenseKey");
				String licenseNumber = results.getString("LicenseNumber");
				Date licenseIssue = new Date(results.getTimestamp("LicenseIssue").getTime());
				Date licenseExpiration = new Date(results.getTimestamp("LicenseExpiration").getTime());
				Licenses.LicenseStatus licenseStatus = Licenses.LicenseStatus.valueOf(results.getString("LicenseStatus"));
				
				Licenses license = new Licenses(licenseKey, licenseNumber, licenseIssue, licenseExpiration, licenseStatus, licenseCategoryFK);
				licenses.add(license);
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
		return licenses;
	}
	
	/**
	 * Get all Licenses records by licenseNumber.
	 * Runs a SELECT statement and returns a list of Licenses that have the specified licenseNumber.
	 */
	public List<Licenses> getLicensesByLicenseNumber(String licenseNumber) throws SQLException {
		List<Licenses> licenses = new ArrayList<Licenses>();
		String selectLicenses = "SELECT LicenseKey,LicenseNumber,LicenseIssue,LicenseExpiration,LicenseStatus,LicenseCategoryFK "
					+ "FROM Licenses "
					+ "WHERE LicenseNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLicenses);
			selectStmt.setString(1, licenseNumber);
			results = selectStmt.executeQuery();
			while(results.next()) {				
				int licenseKey = results.getInt("LicenseKey");
				Date licenseIssue = new Date(results.getTimestamp("LicenseIssue").getTime());
				Date licenseExpiration = new Date(results.getTimestamp("LicenseExpiration").getTime());
				Licenses.LicenseStatus licenseStatus = Licenses.LicenseStatus.valueOf(results.getString("LicenseStatus"));
				String licenseCategoryFK = results.getString("LicenseCategoryFK");
				
				Licenses license = new Licenses(licenseKey, licenseNumber, licenseIssue, licenseExpiration, licenseStatus, licenseCategoryFK);
				licenses.add(license);
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
		return licenses;
	}
	
	/**
	 * Get all Licenses records by licenseStatus.
	 * Runs a SELECT statement and returns a list of Licenses that are of the specified licenseCategoryFK.
	 */
	public List<Licenses> getLicensesByLicenseStatus(Licenses.LicenseStatus licenseStatus) throws SQLException {
		List<Licenses> licenses = new ArrayList<Licenses>();
		String selectLicenses = "SELECT LicenseKey,LicenseNumber,LicenseIssue,LicenseExpiration,LicenseStatus,LicenseCategoryFK "
					+ "FROM Licenses "
					+ "WHERE LicenseStatus=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLicenses);
			selectStmt.setString(1, licenseStatus.name());
			results = selectStmt.executeQuery();
			while(results.next()) {				
				int licenseKey = results.getInt("LicenseKey");
				String licenseNumber = results.getString("LicenseNumber");
				Date licenseIssue = new Date(results.getTimestamp("LicenseIssue").getTime());
				Date licenseExpiration = new Date(results.getTimestamp("LicenseExpiration").getTime());
				String licenseCategoryFK = results.getString("LicenseCategoryFK");
				
				Licenses license = new Licenses(licenseKey, licenseNumber, licenseIssue, licenseExpiration, licenseStatus, licenseCategoryFK);
				licenses.add(license);
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
		return licenses;
	}
	
	/**
	 * Update the licenseNumber of a License instance.
	 * Runs an UPDATE statement.
	 */
	public Licenses updateLicenseNumber(Licenses license, String newLicenseNumber) throws SQLException {
		String updateLicense = "UPDATE Licenses SET LicenseNumber=? WHERE LicenseKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLicense);
			updateStmt.setString(1, newLicenseNumber);
			updateStmt.setInt(2, license.getLicenseKey());
			updateStmt.executeUpdate();

			license.setLicenseNumber(newLicenseNumber);
			return license;
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
	
	/**
	 * Update the licenseStatus of a License instance.
	 * Runs an UPDATE statement.
	 */
	public Licenses updateLicenseStatus(Licenses license, Licenses.LicenseStatus newLicenseStatus) throws SQLException {
		String updateLicense = "UPDATE Licenses SET LicenseStatus=? WHERE LicenseKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLicense);
			updateStmt.setString(1, newLicenseStatus.name());
			updateStmt.setInt(2, license.getLicenseKey());
			updateStmt.executeUpdate();

			license.setLicenseStatus(newLicenseStatus);
			return license;
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
	
	/**
	 * Update the expiration date of a License instance.
	 * Runs an UPDATE statement.
	 */
	public Licenses updateLicenseExpiration(Licenses license, java.util.Date newLicenseExpiration) throws SQLException {
		String updateLicense = "UPDATE Licenses SET LicenseExpiration=? WHERE LicenseKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLicense);
			updateStmt.setTimestamp(1, new Timestamp(newLicenseExpiration.getTime()));
			updateStmt.setInt(2, license.getLicenseKey());
			updateStmt.executeUpdate();

			license.setLicenseExpiration(newLicenseExpiration);
			return license;
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
	
	/**
	 * Delete a Licenses instance.
	 * Runs a DELETE statement.
	 */
	public Licenses delete(Licenses license) throws SQLException {
		String deleteLicense = "DELETE FROM Licenses WHERE LicenseKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLicense);
			deleteStmt.setInt(1, license.getLicenseKey());
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