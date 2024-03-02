package estm.dsic.jee.controllers.note.interfaces;

import java.util.Vector;

import estm.dsic.jee.beans.Note;

public interface INoteController {
    Note createNote(Note note);
    Note update(Note note);
    Note delete(Note note);
    Vector<Note> getAllNotes();
    Vector<Note> findNotes(String query);
}
