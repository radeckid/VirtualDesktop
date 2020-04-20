//Klasa do obsługi tabeli User w bazie danych

package vs.api.database;

import vs.api.helpers.EncryptionDecryption;
import vs.api.repository.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDBO extends DBO<User> {
    public UserDBO(Connection connection) {
        super(connection);
        dataList = new ArrayList<User>();
    }

    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        Statement statement = _connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM Users ORDER BY Surname ASC;");

        while (results.next()) {
            User userFromDatabase = new User();
            userFromDatabase.setId(results.getInt("UserId"));
            userFromDatabase.setName(results.getString("Name"));
            userFromDatabase.setSurname(results.getString("Surname"));
            userFromDatabase.setEmail(results.getString("Email"));
            userFromDatabase.setDataFreeAmount(results.getInt("DataFreeAmount"));
            userFromDatabase.setUserType(results.getInt("AccountType"));
            users.add(userFromDatabase);
        }

        return users;
    }

    public User get(String email, String password) throws SQLException, IllegalArgumentException {
        Statement statement = _connection.createStatement();

        String encryptedPassword = EncryptionDecryption.encrypt(password);

        String query = "SELECT * FROM Users where Email=\'" + email + "\' AND Password=\'" + encryptedPassword + "\';";

        ResultSet results = statement.executeQuery(query);

        if (results.next() == false) {
            throw new IllegalArgumentException("Not found any user with provided email and password. Please try again.");
        }

        User userFromDatabase;

        userFromDatabase = new User();
        userFromDatabase.setId(results.getInt("UserId"));
        userFromDatabase.setName(results.getString("Name"));
        userFromDatabase.setSurname(results.getString("Surname"));
        userFromDatabase.setEmail(results.getString("Email"));
        userFromDatabase.setDataFreeAmount(results.getInt("DataFreeAmount"));
        userFromDatabase.setUserType(results.getInt("AccountType"));


        statement.close();
        return userFromDatabase;
    }

    public int getSpace(String email) {
        Statement statement = null;
        try {
            statement = _connection.createStatement();

            String query = "SELECT DataFreeAmount FROM Users where Email=\'" + email + "\';";

            ResultSet results = null;
            results = statement.executeQuery(query);

            if (!results.next()) {
                throw new IllegalArgumentException("Not found any user with provided email and password. Please try again.");
            }

            return results.getInt("DataFreeAmount");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void setSpace(String userId, int freeSpace) {
        try {
            Statement statement = _connection.createStatement();
            String query = "UPDATE Users SET DataFreeAmount = " + freeSpace + " WHERE UserId = '" + userId + "';";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(User data) throws SQLException, IllegalArgumentException {
        Statement statement = _connection.createStatement();

        String encryptedPassword = EncryptionDecryption.encrypt(data.getPassword());

        if (find(data.getEmail())) {
            throw new IllegalArgumentException("Provided email is not available.");
        } else if (encryptedPassword == null || encryptedPassword.equals("null") || encryptedPassword.equals("")) {
            throw new IllegalArgumentException("Server-Side Error.");
        } else {
            String query = "INSERT INTO Users VALUES (null,'" + data.getName() + "','" + data.getSurname() + "','" + data.getEmail() + "','" + encryptedPassword + "','" + 1024 + "',2);";
            statement.executeUpdate(query);
        }

        statement.close();
    }

    public boolean find(String email) throws SQLException {
        Statement statement = _connection.createStatement();

        String query = "SELECT UserId FROM Users where Email=\'" + email + "\';";

        ResultSet results = statement.executeQuery(query);

        if (results.next() == false) {
            return false;
        }
        statement.close();
        return true;
    }

    public void edit(String name, String surname, String email, String oldEmail) {
        Statement statement = null;
        try {
            statement = _connection.createStatement();
            statement.executeUpdate("UPDATE Users SET Name='" + name + "', Surname='" + surname + "', Email='" + email + "' WHERE Email='" + oldEmail + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        // TODO Auto-generated method stub
    }

    public void delete(int id) {
        Statement statement = null;
        try {
            statement = _connection.createStatement();
            statement.executeUpdate("DELETE FROM Users WHERE UserId=" + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
