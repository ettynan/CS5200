package food.dal;

import food.model.*;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Establishments table in your MySQL
 * instance. This is used to store {@link Establishments} into your MySQL instance and retrieve 
 * {@link Establishments} from MySQL instance.
 */
public class EstablishmentsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static EstablishmentsDao instance = null;
	protected EstablishmentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static EstablishmentsDao getInstance() {
		if(instance == null) {
			instance = new EstablishmentsDao();
		}
		return instance;
	}

	/**
	 * Save the Establishments instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Establishments create(Establishments establishment) throws SQLException {
		String insertEstablishment = "INSERT INTO Establishments(EstablishmentKey, EstablishmentName, EstablishmentOwner, LicenseFK)"
				+ " VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEstablishment);
	        insertStmt.setInt(1, (establishment.getEstablishmentKey()));
	        insertStmt.setString(2, (establishment.getEstablishmentName()));
	        insertStmt.setString(3, (establishment.getEstablishmentOwner()));
	        insertStmt.setInt(4, (establishment.getLicenseFK()));
			insertStmt.executeUpdate();
			return establishment;
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
	 * Delete the Establishments instance.
	 * This runs a DELETE statement.
	 */
	public Establishments deleteByName(String estabName) throws SQLException {
		String deleteEstablishment = "DELETE FROM Establishments WHERE EstablishmentName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteEstablishment);
			deleteStmt.setString(1, estabName);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Establishments instance.
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

	/**
	 * Get the Establishments record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Establishments instance.
	 */
	public Establishments getEstablishmentByKey(int establishmentKey) throws SQLException {
		String selectEstablishment = "SELECT * FROM Establishments WHERE EstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEstablishment);
			selectStmt.setInt(1, establishmentKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int estabKey = results.getInt("EstablishmentKey");
				String estabName = results.getString("EstablishmentName");
				String estabOwner = results.getString("EstablishmentOwner");
				int licenseFK = results.getInt("LicenseFK");				
				Establishments establishment = new Establishments(estabKey, estabName, estabOwner, licenseFK);
				return establishment;
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
	
	public Establishments getEstablishmentByName(String establishmentName) throws SQLException {
		String selectEstablishment = "SELECT * FROM Establishments WHERE EstablishmentName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEstablishment);
			selectStmt.setString(1, establishmentName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int estabKey = results.getInt("EstablishmentKey");
				String estabName = results.getString("EstablishmentName");
				String estabOwner = results.getString("EstablishmentOwner");
				int licenseFK = results.getInt("LicenseFK");				
				Establishments establishment = new Establishments(estabKey, estabName, estabOwner, licenseFK);
				return establishment;
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
	
	public List<Establishments> getEstablishmentsByOwner(String establishmentOwner) throws SQLException {
		List<Establishments> establishments = new ArrayList<Establishments>();
		String selectEstablishment = "SELECT * FROM Establishments WHERE EstablishmentOwner=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEstablishment);
			selectStmt.setString(1, establishmentOwner);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int estabKey = results.getInt("EstablishmentKey");
				String estabName = results.getString("EstablishmentName");
				String estabOwner = results.getString("EstablishmentOwner");
				int licenseFK = results.getInt("LicenseFK");				
				Establishments establishment = new Establishments(estabKey, estabName, estabOwner, licenseFK);
				establishments.add(establishment);
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
		return establishments;
	}	
	
	
}
