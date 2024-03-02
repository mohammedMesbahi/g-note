package estm.dsic.jee.business.interfaces;

import java.util.Vector;

import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface INoteServices {
//    Vector<Note> getAllNotes();
    Vector<Note> get();
    Response addNote(Note note);
    Response updateNote(Note note);
    Response deleteNote(Note note);
    Vector<Note> findNotes(@PathParam("query") String query);
}
