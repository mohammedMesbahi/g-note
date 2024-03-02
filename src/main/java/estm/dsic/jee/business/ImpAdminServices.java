package estm.dsic.jee.business;

import estm.dsic.jee.beans.User;
import estm.dsic.jee.business.interfaces.IAdminServices;
import estm.dsic.jee.controllers.admin.DefaultAdminController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/admin")
public class ImpAdminServices implements IAdminServices {
    @Inject
    DefaultAdminController defaultAdminController;
    @Path("/user/verify")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @POST
    public Response verifyUser(User user) {
        User updatedUser =  defaultAdminController.verifyUser(user);
        if (updatedUser != null) {
            return Response.ok(updatedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    @Override
    public Response delete(User user) {
        User deletedUser = defaultAdminController.removeUser(user);
        if (deletedUser != null) {
            return Response.ok(deletedUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.ok(defaultAdminController.getAllUsers()).build();
    }

}
