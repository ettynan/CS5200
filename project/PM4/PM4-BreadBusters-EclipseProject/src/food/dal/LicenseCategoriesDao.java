package food.dal;

import food.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interacts with {@link LicenseCategories} model for CRUD operations.
 * @author Clara Mae Wells
 */
public class LicenseCategoriesDao {
	// Singleton pattern to limit instantiation to one object.
	protected ConnectionManager connectionManager;
	
	private static LicenseCategoriesDao instance = null;
	
	protected LicenseCategoriesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LicenseCategoriesDao getInstance() {
		if(instance == null) {
			instance = new LicenseCategoriesDao();
		}
		return instance;
	}
	
	/**
	 * Create a new LicenseCategories instance. 
	 * Runs an INSERT statement.
	 */
	public LicenseCategories create(LicenseCategories licenseCategories) throws SQLException {
		String insertLicenseCategories =
			"INSERT INTO LicenseCategories(LicenseCategory,LicenseCategoryDescription) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLicenseCategories);
			insertStmt.setString(1, licenseCategories.getLicenseCategory());
			insertStmt.setString(2, licenseCategories.getLicenseCategoryDescription());
			insertStmt.executeUpdate();
			return licenseCategories;
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
	 * Get LicenseCategories record by licenseCategoryName.
	 * Runs a SELECT statement and returns a single LicenseCategories instance.
	 */
	public LicenseCategories getLicenseCategoriesByLicenseCategory(String licenseCategory) throws SQLException {
		String selectLicenseCategory = "SELECT LicenseCategory,LicenseCategoryDescription FROM LicenseCategories WHERE LicenseCategory=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLicenseCategory);
			selectStmt.setString(1, licenseCategory);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String licenseCategoryDescription = results.getString("LicenseCategoryDescription");
				LicenseCategories licenseCategories = new LicenseCategories(licenseCategory, licenseCategoryDescription);
				return licenseCategories;
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
	 * Delete a LicenseCategories instance.
	 * Runs a DELETE statement.
	 */
	public LicenseCategories delete(LicenseCategories licenseCategories) throws SQLException {
		String deleteLicenseCategories = "DELETE FROM LicenseCategories WHERE LicenseCategory=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLicenseCategories);
			deleteStmt.setString(1, licenseCategories.getLicenseCategory());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for LicenseCategory=" + licenseCategories.getLicenseCategory());
			}
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