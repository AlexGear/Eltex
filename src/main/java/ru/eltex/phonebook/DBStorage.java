package ru.eltex.phonebook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStorage implements PhoneBookStorage {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/phonebook";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "ausrotten";

    private final String tableName;

    public DBStorage(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        final String sql = "SELECT * FROM " + tableName;
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone");
                users.add(new User(id, name, phoneNumber));
            }
            return users;
        }
    }

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
                ResultSet rs = statement.executeQuery(selectLastInsertIdSql);
                rs.next();
                int id = rs.getInt(1);
                user.setId(id);
                return user;
            }
        }
    }

    @Override
    public boolean removeUserById(int id) throws Exception {
        final String sql = "DELETE FROM " + tableName + " WHERE id = " + id;
        try (Connection connection = connect()) {
            try (Statement statement = connection.createStatement()) {
                return 1 == statement.executeUpdate(sql);
            }
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, LOGIN, PASSWORD);
    }
}
