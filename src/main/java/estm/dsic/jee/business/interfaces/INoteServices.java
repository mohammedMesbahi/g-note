package estm.dsic.jee.business.interfaces;

import java.util.Vector;

import estm.dsic.jee.beans.Note;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface INoteServices {
    Vector<Note> getAllNotes();
    Response addNote(Note note);
    Response updateNote(Note note);
    Response deleteNote(Note note);
    Vector<Note> findNotes(@PathParam("query") String query);
}
