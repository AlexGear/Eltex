package ru.eltex.phonebook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class providing access to the phone book MySQL database.
 * The connections are open only when invoking methods
 */
public class DBStorage implements PhoneBookStorage {
    private static final String CONNECTION_URL = "jdbc:mysql://172.17.0.1:3306/phonebook";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "ausrotten";

    private final String tableName;

    /**
     * Allocates {@link DBStorage} object for further work with database table
     * @param tableName The name of database table to work with
     */
    public DBStorage(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get list of all the users from the table
     * @return List of {@link User}s obtained from the table
     * @throws SQLException Thrown if something went wrong while interacting the database
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        final String sql = "SELECT * FROM " + tableName;
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            try(ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phone");
                    users.add(new User(id, name, phoneNumber));
                }
            }
            return users;
        }
    }

    /**
     * Creates new user in the database table with automatically assigned ID
     * @param name The name of the new user
     * @param phoneNumber The phone number of the new user
     * @return The instance of created {@link User} with automatically assigned ID
     * @throws SQLException Thrown if something went wrong while interacting the database
     * @throws IllegalArgumentException Thrown if {@code name} or {@code phoneNumber} is invalid
     */
    @Override
    public User insertNewUser(String name, String  phoneNumber) throws SQLException, IllegalArgumentException {
        User user = new User(0, name, phoneNumber);

        final String insertSql = "INSERT INTO " + tableName + " (name, phone) VALUE (?, ?)";
        final String selectLastInsertIdSql = "SELECT LAST_INSERT_ID()";

        try (Connection connection = connect()) {
            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setString(1, name);
                statement.setString(2, phoneNumber);
                statement.executeUpdate();
            }
            try (Statement statement = connection.createStatement()) {
                try(ResultSet rs = statement.executeQuery(selectLastInsertIdSql)) {
                    rs.next();
                    int id = rs.getInt(1);
                    user.setId(id);
                    return user;
                }
            }
        }
    }

    /**
     * Tries to remove user from the table by its ID
     * @param id THe ID of user to remove
     * @return {@literal true} if removed successfully, {@literal false} if user with provided ID was not found
     * @throws SQLException Thrown if something went wrong while interacting the database
     */
    @Override
    public boolean removeUserById(int id) throws SQLException {
        final String sql = "DELETE FROM " + tableName + " WHERE id = " + id;
        try (Connection connection = connect()) {
            try (Statement statement = connection.createStatement()) {
                return 1 == statement.executeUpdate(sql);
            }
        }
    }

    /**
     * Opens a connection to database using {@link DBStorage#CONNECTION_URL} as connection url,
     * logging in with {@link DBStorage#LOGIN} and {@link DBStorage#PASSWORD}
     * @return The open {@link Connection} instance
     * @throws SQLException Thrown if something went wrong while interacting the database
     */
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, LOGIN, PASSWORD);
    }
}
