package estm.dsic.jee.controllers.note.interfaces;

import java.io.Serializable;
import java.util.Vector;

import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;

public interface INoteController extends Serializable {
    Note createNote(Note note);
    Note update(Note note);
    Note delete(Note note);
    Vector<Note> getAllNotes();
    Vector<Note> get(User user);
    Vector<Note> findNotes(String query);
}
