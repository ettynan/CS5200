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
public class LiquorEstablishmentsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static LiquorEstablishmentsDao instance = null;
	protected LiquorEstablishmentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static LiquorEstablishmentsDao getInstance() {
		if(instance == null) {
			instance = new LiquorEstablishmentsDao();
		}
		return instance;
	}

	/**
	 * Save the Persons instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public LiquorEstablishments create(LiquorEstablishments liquorEstablishment) throws SQLException {
		String insertLiquorEstablishment = "INSERT INTO LiquorEstablishments(LiquorEstablishmentKey, EstablishmentFK,"
				+ "LiquorEstablishmentComments, TimeClose, TimePatronsOffPremises, Capacity)"
				+ " VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLiquorEstablishment);
	        insertStmt.setInt(1, (liquorEstablishment.getLiquorEstablishmentKey()));
	        insertStmt.setInt(2, (liquorEstablishment.getEstablishmentFK()));
	        insertStmt.setString(3, (liquorEstablishment.getComments()));
	        insertStmt.setString(4, (liquorEstablishment.getTimeClose()));
	        insertStmt.setString(5, (liquorEstablishment.getTimePatronsOffPremises()));
	        insertStmt.setInt(6, liquorEstablishment.getCapacity());
			insertStmt.executeUpdate();
			return liquorEstablishment;
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
	public LiquorEstablishments deleteByKey(int liquorEstabKey) throws SQLException {
		String deleteLiquorEstablishment = "DELETE FROM LiquorEstablishments WHERE LiquorEstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLiquorEstablishment);
			deleteStmt.setInt(1, liquorEstabKey);
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

	
	public LiquorEstablishments getLiquorEstablishmentByKey(int liquorEstablishmentKey) throws SQLException {
		String selectLiquorEstablishment = "SELECT * FROM LiquorEstablishments WHERE LiquorEstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLiquorEstablishment);
			selectStmt.setInt(1, liquorEstablishmentKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int liquorEstabKey = results.getInt("LiquorEstablishmentKey");
				int estabKey = results.getInt("EstablishmentFK");
				String comments = results.getString("LiquorEstablishmentComments");
				String timeClose = results.getString("TimeClose");
				String patronsOff = results.getString("TimePatronsOffPremises");
				int capacity = results.getInt("Capacity");
				LiquorEstablishments liquorEstablishment = new LiquorEstablishments(liquorEstabKey, estabKey, comments, timeClose, patronsOff, capacity);
				return liquorEstablishment;
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
	
	public LiquorEstablishments getLiquorEstablishmentByEstablishmentKey(int establishmentKey) throws SQLException {
		String selectLiquorEstablishment = "SELECT * FROM LiquorEstablishments WHERE EstablishmentFK=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLiquorEstablishment);
			selectStmt.setInt(1, establishmentKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int liquorEstabKey = results.getInt("LiquorEstablishmentKey");
				int estabKey = results.getInt("EstablishmentFK");
				String comments = results.getString("LiquorEstablishmentComments");
				String timeClose = results.getString("TimeClose");
				String patronsOff = results.getString("TimePatronsOffPremises");
				int capacity = results.getInt("Capacity");
				LiquorEstablishments liquorEstablishment = new LiquorEstablishments(liquorEstabKey, estabKey, comments, timeClose, patronsOff, capacity);
				return liquorEstablishment;
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
