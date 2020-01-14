package food.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connect database instance.
 */
public class ConnectionManager {
	// User to connect to your database instance. By default, this is "root2".
	private final String user = "root";
	// Password for the user.
	private final String password = "password";
	// URI to your database server. If running on the same machine, then this is "localhost".
	private final String hostName = "localhost";
	// Port to your database server. By default, this is 3307.
	private final int port= 3306;
	// Name of the MySQL schema that contains your tables.
	private final String schema = "Inspections";

	/** Get the connection to the database instance. */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.put("user", this.user);
			connectionProperties.put("password", this.password);
			// Ensure the JDBC driver is loaded by retrieving the runtime Class descriptor.
			// Otherwise, Tomcat may have issues loading libraries in the proper order.
			// One alternative is calling this in the HttpServlet init() override.
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(
			    "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.schema,
			    connectionProperties);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return connection;
	}

	/** Close the connection to the database instance. */
	public void closeConnection(Connection connection) throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}