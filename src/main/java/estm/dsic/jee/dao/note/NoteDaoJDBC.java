package estm.dsic.jee.dao.note;

import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.note.interfaces.NoteDao;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Timestamp;

@Named
@SessionScoped
public class NoteDaoJDBC implements NoteDao, Serializable {
    @Resource(lookup = "jdbc/tp2_jee")
    private DataSource ds;
    Note note;

    @Override
    public Note get(Integer t) {
        try(Connection conn= ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notes WHERE id = ?");
            stmt.setInt(1, t);
            ResultSet rs = stmt.executeQuery();
           rs.next();
           note.setId(t);
           note.setSubject(rs.getString("subject"));
           note.setBody(rs.getString("body"));
           Timestamp timestamp = rs.getTimestamp("date_time");
            note.setDate_time(timestamp.toLocalDateTime());
           note.setId_user(rs.getInt("id_user"));
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return note;
    }

    
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


   public Note update(Note note){
    try (Connection conn= ds.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement("UPDATE notes SET subject=?, body=?, date_time=?, id_user=? WHERE id=?");
        stmt.setString(1, note.getSubject());
        stmt.setString(2, note.getBody());
        Timestamp timestamp = Timestamp.valueOf(note.getDate_time());
        stmt.setTimestamp(3, timestamp);
        stmt.setInt(4, note.getId_user());
        stmt.setInt(5, note.getId());
        int rowUpdated=stmt.executeUpdate();
        if (rowUpdated > 0) {
            return note;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
   }

    public Vector<Note> getAllNotes(){
        Vector<Note> notes = new Vector<>();
        try (Connection conn= ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                note = new Note();
                note.setId(rs.getInt("id"));
                note.setSubject(rs.getString("subject"));
                note.setBody(rs.getString("body"));
                Timestamp timestamp = rs.getTimestamp("date_time");
                note.setDate_time(timestamp.toLocalDateTime());
                note.setId_user(rs.getInt("id_user"));
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
    public Vector<Note> findNotes(String query){
        Vector<Note> notes = new Vector<>();
        query = "%"+query+"%";
        try (Connection conn= ds.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notes WHERE subject like ? OR body like ?");
            stmt.setString(1, query);
            stmt.setString(2, query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                note = new Note();
                note.setId(rs.getInt("id"));
                note.setSubject(rs.getString("subject"));
                note.setBody(rs.getString("body"));
                Timestamp timestamp = rs.getTimestamp("date_time");
                note.setDate_time(timestamp.toLocalDateTime());
                note.setId_user(rs.getInt("id_user"));
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
    
    @Override
   public Note delete(Note note){
    
    try (Connection conn = ds.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM notes WHERE id = ?");
        stmt.setInt(1, note.getId());
        int rowsDeleted = stmt.executeUpdate();
        if (rowsDeleted > 0) {
            return note;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
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
