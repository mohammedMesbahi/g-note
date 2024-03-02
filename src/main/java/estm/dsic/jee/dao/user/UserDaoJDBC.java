package estm.dsic.jee.dao.user;

import java.io.Serializable;
import java.sql.*;
import java.util.Vector;

import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.user.interfaces.UserDao;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Named
@SessionScoped
public class UserDaoJDBC implements UserDao, Serializable {
    @Resource(lookup = "jdbc/tp3_jee")
    private DataSource ds;
    public UserDaoJDBC() {
        /*try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/tp2_jee");
        } catch (NamingException e) {
            e.printStackTrace();
        }*/
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
                user.setId(resultSet.getInt("id"));
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
                user.setId(resultSet.getInt(1));
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
        User newUser = null;
        try {
            String query = "UPDATE user SET name=?, email=?, password=?, isVerified=?, isAdmin=? WHERE id=?";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isVerified());
            statement.setBoolean(5, user.isAdmin());
            statement.setInt(6, user.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                newUser = user;
            }

        } catch (SQLException e) {
            System.out.println("Error while updating user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return newUser;
    }

    @Override
    public User delete(User user) {
        User deletedUser = null;
        try {
            String query = "DELETE FROM user WHERE id=?";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setInt(1, user.getId());
            int deletedRows = statement.executeUpdate();
            if (deletedRows > 0) {
                deletedUser = user;
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return deletedUser;
    }

    @Override
    public Vector<User> getAll() {
        Vector<User> users = new Vector<>();
        try {
            String query = "SELECT * FROM user where !isadmin";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setVerified(resultSet.getBoolean("isverified"));
                user.setAdmin(resultSet.getBoolean("isadmin"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error while getting all users");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User get(User user) {
        User user1;
        try {
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setName(resultSet.getString("name"));
                user1.setEmail(resultSet.getString("email"));
                user1.setPassword(resultSet.getString("password"));
                user1.setVerified(resultSet.getBoolean("isverified"));
                user1.setAdmin(resultSet.getBoolean("isadmin"));
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
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setVerified(resultSet.getBoolean("isverified"));
                user.setAdmin(resultSet.getBoolean("isadmin"));
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
    public User verify(User user) {
        User verifiedUser = null;
        try {
            String query = "UPDATE user SET isverified = ? WHERE id = ?";
            PreparedStatement statement = ds.getConnection().prepareStatement(query);
            statement.setBoolean(1, user.isVerified());
            statement.setInt(2, user.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                verifiedUser = user;
            }
        } catch (SQLException e) {
            System.out.println("Error while verifying user");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return verifiedUser;
    }
}
