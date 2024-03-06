package estm.dsic.jee.business;
import estm.dsic.jee.beans.User;
import estm.dsic.jee.business.interfaces.IAuthServices;
import estm.dsic.jee.controllers.auth.DefaultAuthController;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class ImpAuthServices implements IAuthServices{
    @Context
    private HttpServletResponse response ;
    @Inject
    DefaultAuthController authController;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response authenticate(User u) {
        User user = authController.authenticate(u);
        if (user != null) {
            if(user.isAdmin() || user.isVerified()){
                user.setPassword(null);
                NewCookie idNewCookie = new NewCookie("userID", user.getId().toString(), "/", null, null, 60*60*24, false);

                return Response.status(Response.Status.OK)
                        .cookie(idNewCookie)
                        .entity(user).build();
            } else{
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Please wait tell you are verified.")
                        .build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid Credentials.")
                    .build();
        }
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response register(User user) {
        // check if all fields are valid
        if (user.getEmail() == null ||
                user.getPassword() == null ||
                user.getName() == null ||
                user.getEmail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getName().isEmpty()) {

            // message : "All fields are required"
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("All fields are required")
                    .build();
        }
        User newUser = authController.register(user);
        if (newUser != null) {
            return Response.status(Response.Status.CREATED).entity(newUser).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error while creating user")
                    .build();
        }
    }

    @POST
    @Path("logout")
    public Response logout(@Context HttpServletResponse response) {
        // Invalidate the existing authentication cookie (assuming you have one)
        NewCookie idNewCookie = new NewCookie("userID","");


        // Perform any other logout-related operations

        return Response.status(Response.Status.OK)
                .cookie(idNewCookie)
                .entity("Logout successful").build();
    }

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {

        NewCookie idNewCookie = new NewCookie("userID", String.valueOf(10), "/", null, null, 60*60*24, true);

        return Response.status(Response.Status.OK)
                .cookie(idNewCookie)
                .entity("welcome to cabol").build();
    }
}
