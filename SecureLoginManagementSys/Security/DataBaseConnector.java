package Security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The `DataBaseConnector` class is responsible for establishing database
 * connections
 * to the demo database using different user credentials.
 * It provides a getConnection method that allows selecting a database
 * connection
 * based on the user's index.
 */
public class DataBaseConnector {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/demo";
    private static final String[] userName = { "temporary", "customer", "Employee", "manager" };
    private static final String[] userpwd = { "Temporary", "Customer", "Employee", "Manager" };

    /**
     * Get a database connection based on the user index.
     *
     * @param index The index of the user for selecting the appropriate credentials.
     * @return A database connection.
     */
    public Connection getConnection(int index) {
        Connection connection = null;
        String user = userName[index];
        String password = userpwd[index];
        try {
            Class.forName("org.postgresql.Driver");// Load the PostgreSQL JDBC driver class
            connection = DriverManager.getConnection(dbUrl, user, password);// Create a database connection
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
