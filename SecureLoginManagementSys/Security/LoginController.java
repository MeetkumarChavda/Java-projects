package Security;

// package import
//java in built
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final LoginController loginController = new LoginController();
    protected static int userIdNumber;
    protected static boolean isLoged = false;
    protected static String authentication = null;

    // Entry point for user login
    protected static boolean userlogInResponse() {
        return loginController.loginResponse();
    }

    // Handle user's login or signup choice
    private boolean loginResponse() {
        scanner.nextLine();
        while (true) {
            System.out.print("Enter Choice (sign in/sign up) or 'back': ");
            String userChoice = scanner.nextLine().toLowerCase().replaceAll("\\s", "");
            if (userChoice.equalsIgnoreCase("back")) {
                return false;
            }
            if (userChoice.equals("signin")) {
                return true;
            } else if (userChoice.equals("signup")) {
                signUp_2();
            } else {
                System.out.println("Invalid input. Please enter <sign in> or <sign up>. or <back>");
            }
        }
    }

    /**
     * Authenticate the user's credentials using a 3-step approach.
     * 
     * @param isLogable A boolean indicating whether the user chose to log in or
     *                  not.
     * @return A boolean indicating whether the authentication was successful.
     */
    protected static boolean authenticateUser(Boolean isLogable) {
        return loginController.authenticateCredentials(isLogable);
    }

    /**
     * Step 1: Authenticate user credentials.
     *
     * @param isLoggable A boolean indicating whether the user chose to log in or
     *                   not.
     * @return A boolean indicating whether the authentication was successful.
     */
    private boolean authenticateCredentials(boolean isLoggable) {
        if (!isLoggable) {
            return false; // User chose to go back, so exit the method
        }
        try (Connection connection = new DataBaseConnector().getConnection(0)) {
            // Ensure the connection is not null
            assert connection != null : "Connection is null";

            // Getting Username and password
            System.out.print("Enter UserName : ");
            String userName = scanner.next();
            System.out.print("Enter Password :");
            String password = scanner.next();

            // Retrieve user information and handle authentication
            if (authenticateCredentials(connection, userName, password)) {
                System.out.println("Authentication successful!");
                return true;
            } else {
                System.out.println("Authentication failed.");
                return false;
            }
        } catch (SQLException e) {
            handleSqlException(e, "");
        }
        return false;
    }

    /**
     * Step 2: Retrieve user information and handle authentication.
     *
     * @param connection A database connection.
     * @param userName   The username provided by the user.
     * @param password   The password provided by the user.
     * @return A boolean indicating whether the authentication was successful.
     * @throws SQLException If a database error occurs.
     */

    private boolean authenticateCredentials(Connection connection, String userName, String password)
            throws SQLException {
        /*
         * SQL query to retrieve user ID and saved Hashed password based on the provided
         * username
         */
        String sql = "SELECT UserID, UserPassword,UserStatus FROM User_information WHERE UserName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            // If a user with the provided username exists in the database
            if (resultSet.next()) {
                /*
                 * Get the user's ID from the result set
                 * Get the saved hashePassword from the result set
                 */
                int userID = resultSet.getInt("UserID");
                String savedPassword = resultSet.getString("UserPassword");
                authentication = resultSet.getString("UserStatus");
                // Retrieve salt and perform authentication
                return authenticateCredentials(connection, userID, password, savedPassword);
            } else {
                System.out.println("No such user found.");
                return false;
            }
        }
    }

    /**
     * Step 3: Retrieve salt and perform authentication.
     *
     * @param connection    A database connection.
     * @param userID        The user's ID.
     * @param password      The password provided by the user.
     * @param savedPassword The hashed password saved in the database.
     * @return A boolean indicating whether the authentication was successful.
     * @throws SQLException If a database error occurs.
     */
    private boolean authenticateCredentials(Connection connection, int userID, String password, String savedPassword)
            throws SQLException {
        /*
         * SQL query to retrieve the salt associated with the user's ID SALT IN byte []
         */
        String sql = "SELECT Salt FROM UserSalts WHERE UserID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                /*
                 * Iterate through the result set to retrieve the salt
                 * Get the salt as a byte array
                 */
                byte[] salt = resultSet.getBytes("Salt");
                // Attempt to authenticate the user's provided password
                try {
                    SecurityHandler securityHandler = new SecurityHandler();
                    if (securityHandler.authenticateUser(password, salt, savedPassword)) {
                        userIdNumber = userID;
                        isLoged = true;
                        return true; // Authentication successful
                    } else {
                        authentication = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false; // Authentication failed
        }
    }

    /**
     * Handles the user registration process securely.
     * Prompts the user to enter a username and password, validates them, and
     * registers the user if valid.
     */
    private void signUp_2() {
        System.out.println("Sign up Selected.");
        System.out.println("Fill in the details below.");

        // Prompt the user for a username and validate it password and validate it
        String userName = promptForValidUsername();
        String password = promptForValidPassword();

        int userId = generateRandomUserId(); // Generate a random User ID

        try (Connection connection = new DataBaseConnector().getConnection(0)) {

            assert connection != null : "Connection is null"; // Ensure the connection is not null
            final SecurityHandler securityHandler = new SecurityHandler();
            // Hash the user's password
            String hashedPassword = hashPassword(password, securityHandler);

            // Insert user information into the 'User_information' table
            insertUserInformation(connection, userId, userName, hashedPassword);

            // Insert user salt into the 'UserSalts' table
            insertUserSalt(connection, securityHandler, userId);

            System.out.println("User registered successfully.");
        } catch (SQLException | NoSuchAlgorithmException e) {
            // Ensure that the exception is of type SQLException
            assert e instanceof SQLException;
            handleSqlException((SQLException) e, userName);
            signUp();
        }
    }

    /**
     * Prompts the user to enter a valid username.
     *
     * @return The validated username.
     */
    private String promptForValidUsername() {
        String userName;
        do {
            System.out.print("Enter username: ");
            userName = scanner.next();
        } while (!isValidUsername(userName));
        return userName;
    }

    /**
     * Prompts the user to enter a valid password.
     *
     * @return The validated password.
     */
    private String promptForValidPassword() {
        String password;
        do {
            System.out.print("Enter password: ");
            password = scanner.next();
        } while (!isValidPassword(password));
        scanner.nextLine(); // Consume the newline character left by next()
        return password;
    }

    // Validate a username
    private boolean isValidUsername(String username) {

        String regex = "^[a-zA-Z0-9_]+$";
        return username.matches(regex);
    }

    // Validate a password
    private boolean isValidPassword(String password) {

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
        // Return true if the username matches the pattern, otherwise false
    }

    // Generate a random User ID
    private int generateRandomUserId() {
        Random random = new Random();
        return random.nextInt(10_000) + 110_000;
        // Generate a random number between 110,000 and 119,999 (inclusive)
    }

    /**
     * Hashes the provided password securely.
     *
     * @param password The password to hash.
     * @return The hashed password.
     * @throws NoSuchAlgorithmException If a hashing algorithm is not available.
     */
    private String hashPassword(String password, SecurityHandler securityHandler) throws NoSuchAlgorithmException {
        return securityHandler.generateHashPassword(password);
    }

    /**
     * Inserts user information into the 'User_information' table.
     *
     * @param connection     The database connection.
     * @param userId         The user's ID.
     * @param userName       The username.
     * @param hashedPassword The hashed password.
     * @throws SQLException If a database error occurs.
     */
    private void insertUserInformation(Connection connection, int userId, String userName, String hashedPassword)
            throws SQLException {
        String sql = "INSERT INTO User_information (UserID, UserName, UserPassword , UserStatus) VALUES (?, ?, ? , ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setString(4, "CUSTOMER");
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Inserts user salt into the 'UserSalts' table.
     *
     * @param connection The database connection.
     * @param userId     The user's ID.
     * @throws SQLException If a database error occurs.
     */
    private void insertUserSalt(Connection connection, SecurityHandler securityHandler, int userId)
            throws SQLException {

        byte[] salt = securityHandler.getSalt();

        String sql = "INSERT INTO UserSalts (UserID, Salt) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setBytes(2, salt);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Handles SQL exceptions, specifically checking for duplicate key violations
     * (unique constraint violations)
     * in PostgreSQL. When a duplicate key violation occurs, it prints an error
     * message indicating that the provided
     * username is already taken and prompts the user to try another one. For other
     * SQL exceptions, it prints
     * the exception stack trace.
     *
     * @param e        The SQL exception to handle.
     * @param userName The username that caused the exception (used for error
     *                 message).
     */

    private void handleSqlException(SQLException e, String userName) {
        if (e instanceof org.postgresql.util.PSQLException
                && "23505".equals(((org.postgresql.util.PSQLException) e).getSQLState())) {
            System.err.println("Duplicate key violation occurred.");
            System.err.println(userName + " username is already taken. Please try another one.");
        } else {
            e.printStackTrace();
        }
    }

    // backup purpose
    private void signUp() {
        System.out.println("Sign up Selected.");
        System.out.println("Fill in the details below.");
        String userName;
        String password;
        String hashedPassword = "";
        /*
         * Prompt the user to enter a username &
         * Validate the username by isValidUsername(userName)
         */
        do {
            System.out.print("Enter username: ");
            userName = scanner.next();
        } while (!isValidUsername(userName));
        /*
         * Prompt the user to enter a password &
         * Validate the password by isValidPassword(password)
         */
        do {
            System.out.print("Enter password: ");
            password = scanner.next();
        } while (!isValidPassword(password));

        scanner.nextLine(); // Consume the newline character left by next()
        int userId = generateRandomUserId();// Generate a random User ID

        try (Connection connection = new DataBaseConnector().getConnection(0)) {

            // Ensure the connection is not null
            assert connection != null : "Connection is null";
            SecurityHandler securityHandler = new SecurityHandler();
            // Hash the user's password
            hashedPassword = securityHandler.generateHashPassword(password);

            try (Statement statement = connection.createStatement()) {
                /*
                 * SQL query to insert user information into the 'User_information' table
                 */
                String sql = "INSERT INTO User_information (UserID, UserName, UserPassword) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setString(2, userName);
                    preparedStatement.setString(3, hashedPassword);
                    preparedStatement.executeUpdate();
                }
                /*
                 * SQL query to insert user salt into the 'UserSalts' table
                 */
                String sql_2 = "INSERT INTO UserSalts (UserID, Salt) VALUES (?, ?)";
                try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql_2)) {
                    preparedStatement2.setInt(1, userId);
                    preparedStatement2.setBytes(2, securityHandler.getSalt());
                    preparedStatement2.executeUpdate();
                }
                System.out.println("User registered successfully.");
            } catch (SQLException e) {
                handleSqlException(e, userName);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            assert e instanceof SQLException;// Ensure that the exception is of type SQLException
            handleSqlException((SQLException) e, userName);// Handle SQL exceptions andre
            // throw them as RuntimeException
        }
    }

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 * To handle a duplicate key exception when working with PostgreSQL using JDBC
 * in Java,
 * you can catch the specific exception `org.postgresql.util.PSQLException` and
 * examine its SQLState to identify the type of error.
 * Here's a step-by-step guide on how to handle duplicate key exceptions:
 * 
 * 1. **Establish a Database Connection**:
 * 
 * Begin by establishing a connection to your PostgreSQL database as previously
 * explained:
 * ```java
 * Connection connection = null;
 * try {
 * connection =
 * DriverManager.getConnection("jdbc:postgresql://localhost:5432/yourdb",
 * "yourusername", "yourpassword");
 * } catch (SQLException e) {
 * e.printStackTrace();
 * }
 * ```
 * 2. **Execute SQL Query**:
 *
 * Execute your SQL query that may cause a duplicate key violation.
 * In this example, we'll use an INSERT statement as an illustration:
 * ```java
 * PreparedStatement preparedStatement = null;
 * try {
 * String insertQuery =
 * "INSERT INTO your_table (column1, column2) VALUES (?, ?)";
 * preparedStatement = connection.prepareStatement(insertQuery);
 * preparedStatement.setInt(1, 42);
 * preparedStatement.setString(2, "value");
 * preparedStatement.executeUpdate();
 * } catch (SQLException e) {
 * if (e instanceof org.postgresql.util.PSQLException &&
 * "23505".equals(((org.postgresql.util.PSQLException) e).getSQLState())) {
 * // Handle duplicate key violation
 * System.err.println("Duplicate key violation occurred.");
 * } else {
 * e.printStackTrace();
 * }
 * } finally {
 * // Close resources
 * try {
 * if (preparedStatement != null) {
 * preparedStatement.close();
 * }
 * if (connection != null) {
 * connection.close();
 * }
 * } catch (SQLException e) {
 * e.printStackTrace();
 * }
 * }
 * ```
 * 
 * 3. **Handle the Duplicate Key Violation**:
 * 
 * In the `catch` block,
 * we check if the caught exception is an instance of
 * `org.postgresql.util.PSQLException` and then examine its SQLState
 * . A SQLState of "23505" corresponds to a unique violation (i.e., a duplicate
 * key violation).
 * 
 * You can replace the `"Duplicate key violation occurred."
 * ` message with your desired handling logic for the duplicate key scenario.
 * This may include logging the error, notifying the user, or taking appropriate
 * corrective action.
 * 
 * 4. **Close Resources**:
 * 
 * Finally, as always,
 * close the `PreparedStatement` and `Connection` objects in a `finally` block
 * to
 * release database resources when you're done with them.
 * 
 * By catching the `org.postgresql.util.PSQLException` and checking its
 * SQLState,
 * you can specifically handle duplicate key violations in your Java application
 * when working with PostgreSQL.
 */
/*
 * 
 * 
 */
//////////////////////////////////////////////////
// Single method approch for authantication

/////////////////////////////////////////////////////////////////////////////////
// private void authenticateCredentials(Boolean isLogable) {
// if (!isLogable) {
// return; // User chose to go back, so exit the method
// }
// try (Connection connection = new DataBaseConnector().getConnection(0)) {
// // Ensure that the connection is not null using assert
// assert connection != null : "Connection is null";

// /*
// * Getting Username and password
// */
// System.out.print("Enter UserName : ");
// String userName = scanner.next();
// System.out.print("Enter Password :");
// String password = scanner.next();

// /**
// * SQL query to retrieve user ID and saved password based on the provided
// * username
// */
// String infoTableQuery = "SELECT UserID, UserPassword FROM User_information
// WHERE UserName = ?";
// try (PreparedStatement preparedStatement =
// connection.prepareStatement(infoTableQuery)) {

// preparedStatement.setString(1, userName);
// ResultSet resultSet = preparedStatement.executeQuery();

// // If a user with the provided username exists in the database
// if (resultSet.next()) {
// /*
// * Get the user's ID from the result set
// * Get the saved password from the result set
// */
// int userID = resultSet.getInt("UserID");
// String savedPassword = resultSet.getString("UserPassword");
// /**
// * SQL query to retrieve the salt associated with the user's ID SALT IN byte
// []
// */
// String saltTableQuery = "SELECT Salt FROM UserSalts WHERE UserID = ?";
// try (PreparedStatement preparedStatement2 =
// connection.prepareStatement(saltTableQuery)) {
// preparedStatement2.setInt(1, userID);
// ResultSet resultSet2 = preparedStatement2.executeQuery();

// while (resultSet2.next()) {
// /*
// * Iterate through the result set to retrieve the salt
// * Get the salt as a byte array
// */
// byte[] salt = resultSet2.getBytes("Salt");
// try {
// SecurityHandler securityHandler = new SecurityHandler();
// // Attempt to authenticate the user's provided password
// if (securityHandler.authenticateUser(password, salt, savedPassword)) {
// System.out.println("Authentication successful!");
// } else {
// System.out.println("Authentication failed.");
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }
// } else {
// // User with the provided username not found
// System.out.println("No such user found.");
// }
// }
// } catch (SQLException e) {
// handleSqlException(e, "Try again !");
// }
// }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// One strep approch for sign up
//////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 * @param is called a "parameter documentation tag."
 * In programming, `@param` is a documentation tag used in comments to provide
 * information about a function or method parameter. It is typically used in
 * code comments, especially in languages like Java, Python, and JavaScript, to
 * document the purpose and expected behavior of parameters. This documentation
 * is helpful for developers who read the code to understand how to use the
 * function correctly.
 * 
 * Here's the typical format of `@param` in code comments:
 * 
 * ```java
 * /**
 * This is a method description.
 *
 * @param parameterName Description of the parameter.
 */
// public void myMethod(int parameterName) {
// // Method implementation
// }
// ```

// In the above example:
// - `@param` is the documentation tag.
// - `parameterName` is the name of the parameter.
// - `Description of the parameter` is a brief description of what the parameter
// is for and what it should contain.

// Including `@param` tags in your code comments is a good practice because it
// helps improve the documentation of your code, making it easier for other
// developers (or even your future self) to understand how to use the method
// correctly and what each parameter represents. Integrated development
// environments (IDEs) often use these comments to generate documentation or
// tooltips for code, enhancing code readability and maintainability.
// */