package estm.dsic.jee.cors;

import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, cookie, Set-Cookie, X-Requested-With, X-Auth-Token, X-Auth-User, X-Auth-Password, X-Auth-Email, X-Auth-Name, X-Auth-Role, X-Auth-Verified, X-Auth-Admin, X-Auth-Id, X-Auth-Expire");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");


    }
}

