package estm.dsic.jee.dao.note.interfaces;


import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.dao.CrudAble;

public interface NoteDao extends CrudAble<Note, Integer> {
    User get(User user);
    User get(String email, String password);
}
