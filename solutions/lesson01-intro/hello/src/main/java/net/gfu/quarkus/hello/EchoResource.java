package net.gfu.quarkus.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.inject.Inject;


@Path("/echo")
public class EchoResource {

    @Inject EchoService service;

    @GET @Path("/{spell}")
    @Produces(MediaType.TEXT_PLAIN)
    public String echo(@PathParam("spell") String spell) {
        return service.echo(spell);
    }
}