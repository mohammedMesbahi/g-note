package estm.dsic.jee.business;

import java.util.Map;
import java.util.Vector;

import estm.dsic.jee.beans.Note;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.business.interfaces.INoteServices;
import estm.dsic.jee.controllers.note.DefaultNoteController;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notes")
public class ImpNoteServices implements INoteServices {
    @Context
    HttpHeaders headers;
    @Context
    HttpHeaders request;
    @Inject
    DefaultNoteController noteController;

    @GET
    @Path("/all/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Vector<Note> get(@PathParam("userId") int id) {
        // parse from the request the cookie token the  id of the user
        //Map<String, Cookie> cookies = headers.getCookies();
        //Cookie tokenCookie = cookies.get("userID");
        Vector<Note> notes=noteController.getAllNotes(id);
        /* if (tokenCookie != null) {
            String userId = tokenCookie.getValue();
            User user = new User();
            user.setId(Integer.parseInt(userId));
            notes=noteController.getAllNotes(user.getId());
        }  */
        return notes;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNote(Note note) {
        Note newNote = noteController.createNote(note);

        if (newNote != null) {
            return Response.ok().entity(newNote).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }


    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNote(Note note) {
        Note updatedNote = noteController.update(note);
        if ( updatedNote!= null) {
            return Response.ok().entity(updatedNote).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNote(Note note) {
        if (noteController.delete(note) != null) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @Path("/search/{query}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vector<Note> findNotes(@PathParam("query") String query) {
        return noteController.findNotes(query);
    }
}
