package estm.dsic.jee.dao.note.interfaces;


import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.CrudAble;

import java.util.Vector;

public interface NoteDao extends CrudAble<Note, Integer> {
    Vector<Note> get(User user);

}
