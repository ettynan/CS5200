package blog.dal;

import blog.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class to interact with the underlying Users
 * table in MySQL instance. This is used to store {@link Users} into your MySQL instance and
 * retrieve {@link Users} from MySQL instance.
 */
public class UsersDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static UsersDao instance = null;

    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }

    public static UsersDao getInstance() {
        if (instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    /**
     * Save the Users instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Users create(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);
            // PreparedStatement allows us to substitute specific types into the query template.
            // For nullable fields, you can check the property first and then call setNull()
            // as applicable.
            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setString(5, user.getEmail());
            insertStmt.setString(6, user.getPhone());
            insertStmt.executeUpdate();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    /**
     * Get the Users record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Users instance.
     */
    public Users getUserFromUserName(String userName) throws SQLException {
        String selectUser = "SELECT UserName,Password,FirstName,LastName,Email,Phone FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, userName);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                String resultUserName = results.getString("UserName");
                String password = results.getString("Password");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String email = results.getString("Email");
                String phone = results.getString("Phone");
                Users user = new Users(resultUserName, password, firstName, lastName, email, phone);
                return user;
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
     * Delete the Users instance.
     * This runs a DELETE statement.
     */
    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUserName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Users instance.
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
