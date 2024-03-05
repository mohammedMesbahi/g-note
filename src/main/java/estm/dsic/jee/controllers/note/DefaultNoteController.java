package estm.dsic.jee.controllers.note;

import java.time.LocalDateTime;
import java.util.Vector;

import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.controllers.note.interfaces.INoteController;
import estm.dsic.jee.dao.note.NoteDaoJDBC;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class DefaultNoteController implements INoteController {
    @Inject
    NoteDaoJDBC noteDaoJDBC;
    @Override
    public Note createNote(Note note) {
        if(note.getDate_time()==null){
            note.setDate_time(LocalDateTime.now());
        }
        return noteDaoJDBC.create(note);
    }

    @Override
    public Note update(Note note) {
        return noteDaoJDBC.update(note);
    }

    @Override
    public Note delete(Note note) {
        return noteDaoJDBC.delete(note);
    }

    @Override
    public Vector<Note> getAllNotes(int id) {
        return noteDaoJDBC.getAllNotes(id);
    }

    @Override
    public Vector<Note> get(User user) {
        return noteDaoJDBC.get(user);
    }

    @Override
    public Vector<Note> findNotes(String query) {
        return noteDaoJDBC.findNotes(query);
    }
}
