package estm.dsic.jee.dao.user;

import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.user.interfaces.UserDao;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Named
@SessionScoped

public class UserDaoJDBC implements UserDao, Serializable {
    @Resource(lookup = "jdbc/tp3_jee")
    private DataSource ds;
    public UserDaoJDBC() {

    }
    @Override
    public User get(Integer id) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE user.id = " + id;
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User create(User user) {
        try {
            // creat a prepared statement
            String query = "INSERT INTO user (name,email, password,isVerified,isAdmin) VALUES (?, ?, ?,?,?)";
            PreparedStatement statement = ds.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isVerified());
            statement.setBoolean(5, user.isAdmin());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println("Error while creating user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User get(User user) {
        User user1;
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user1 = new User();
                user1.setId(resultSet.getLong("id"));
                user1.setName(resultSet.getString("name"));
                user1.setEmail(resultSet.getString("email"));
                user1.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User get(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error while getting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return user;
    }
}
