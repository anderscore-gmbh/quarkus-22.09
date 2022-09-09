package net.gfu.quarkus.echo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/echo")
public class EchoResource {

    @GET
    @Path("/{text}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("text") String text) {
        return text;
    }
}