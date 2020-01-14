package food.dal;

import food.model.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Data access object (DAO) class to interact with the underlying Address table in your MySQL
 * instance. This is used to store {@link s} into your MySQL instance and retrieve 
 * {@link Address} from MySQL instance.
 */
public class AddressesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AddressesDao instance = null;
	protected AddressesDao() {
		connectionManager = new ConnectionManager();
	}
	public static AddressesDao getInstance() {
		if(instance == null) {
			instance = new AddressesDao();
		}
		return instance;
	}

	/**
	 * Save the Address instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Addresses create(Addresses address) throws SQLException {
		String insertAddress = "INSERT INTO Addresses(EstablishmentKey, Street, City, State, ZipCode, PropertyId)"
				+ " VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAddress);
	        insertStmt.setInt(1, (address.getEstablishmentKey()));
	        insertStmt.setString(2, (address.getStreet()));
	        insertStmt.setString(3, (address.getCity()));
	        insertStmt.setString(4, (address.getState()));
	        insertStmt.setInt(5, (address.getZip()));
	        insertStmt.setInt(6, (address.getPropertyId()));
			insertStmt.executeUpdate();
			return address;
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
	 * Delete the Address instance.
	 * This runs a DELETE statement.
	 */
	public Addresses deleteByKey(int estabKey) throws SQLException {
		String deleteAddress = "DELETE FROM Addresses WHERE EstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAddress);
			deleteStmt.setInt(1, estabKey);
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Addresss instance.
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
	
	public Addresses updateAddress(int estabKey, String street, String city, String state, int zip, int propertyId) throws SQLException {
		String updateCreditCard = "UPDATE Addresses SET Street=?, City=?, State=?, ZipCode=?, PropertyId=? WHERE EstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCreditCard);
			AddressesDao addressesDao = AddressesDao.getInstance();
			updateStmt.setString(1, street);
			updateStmt.setString(2, city);
			updateStmt.setString(3, state);
			updateStmt.setInt(4, zip);
			updateStmt.setInt(5, propertyId);
			updateStmt.setInt(6,  estabKey);
			updateStmt.executeUpdate();
			Addresses address = addressesDao.getAddressByKey(estabKey);
			// Update the Address param before returning to the caller.
			address.setStreet(street);
			address.setCity(city);
			address.setState(state);
			address.setZip(zip);
			address.setPropertyId(propertyId);
			return address;
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
	 * Get the Address record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Address instance.
	 */
	public Addresses getAddressByKey(int establishmentKey) throws SQLException {
		String selectAddress = "SELECT * FROM Addresses WHERE EstablishmentKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAddress);
			selectStmt.setInt(1, establishmentKey);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int estabKey = results.getInt("EstablishmentKey");
				String street = results.getString("Street");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("ZipCode");		
				int propertyId = results.getInt("PropertyId");	
				Addresses address = new Addresses(estabKey, street, city, state, zip, propertyId);
				return address;
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
	
	public Addresses getAddressByPropertyId(int propertyId) throws SQLException {
		String selectAddress = "SELECT * FROM Addresses WHERE propertyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAddress);
			selectStmt.setInt(1, propertyId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int estabKey = results.getInt("EstablishmentKey");
				String street = results.getString("Street");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("ZipCode");		
				int propertyID = results.getInt("PropertyId");					
				Addresses address = new Addresses(estabKey, street, city, state, zip, propertyID);
				return address;
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
	
	public List<Addresses> getAddressesByZip(int zip) throws SQLException {
		List<Addresses> addresss = new ArrayList<Addresses>();
		String selectAddress = "SELECT * FROM Addresses WHERE ZipCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAddress);
			selectStmt.setInt(1, zip);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int estabKey = results.getInt("EstablishmentKey");
				String street = results.getString("Street");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip1 = results.getInt("ZipCode");		
				int propertyID = results.getInt("PropertyId");					
				Addresses address = new Addresses(estabKey, street, city, state, zip1, propertyID);
				addresss.add(address);
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
		return addresss;
	}	
	
	
}
