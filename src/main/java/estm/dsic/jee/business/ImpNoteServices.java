package estm.dsic.jee.business;

import java.net.http.HttpRequest;
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
import jakarta.ws.rs.core.*;


@Path("/notes")
public class ImpNoteServices implements INoteServices {
    @Context
    HttpHeaders headers;
    @Inject
    DefaultNoteController noteController;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Vector<Note> get() {
        // parse from the request the cookie token the  id of the user
        Map<String, Cookie> cookies = headers.getCookies();
        Cookie tokenCookie = cookies.get("userID");

        if (tokenCookie != null) {
            String userId = tokenCookie.getValue();
            User user = new User();
            user.setId(Integer.parseInt(userId));
            return noteController.get(user);
        } else {
            return null;
        }
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
        if (noteController.update(note) != null) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @DELETE
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
