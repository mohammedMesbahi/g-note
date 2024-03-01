package estm.dsic.jee.dao.note;

import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.note.interfaces.NoteDao;
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
public class NoteDaoJDBC implements NoteDao, Serializable {
    @Resource(lookup = "jdbc/tp2_jee")
    private DataSource ds;

    @Override
    public Note get(Integer t) {
        return null;
    }

    @Override
    public Note create(Note note) {
        try {
            // creat a prepared statement
            String query = "INSERT INTO note (title, content, user_id) VALUES (?, ?, ?)";
            PreparedStatement statement = ds.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, note.getSubject());
            statement.setString(2, note.getBody());
            statement.setInt(3, note.getId_user());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                note.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error while creating note");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
        return note;
    }

    @Override
    public Note update(Note note) {
        return null;
    }

    @Override
    public Note delete(Note note) {
        return null;
    }

    @Override
    public User get(User user) {
        return null;
    }

    @Override
    public User get(String email, String password) {
        return null;
    }
}
