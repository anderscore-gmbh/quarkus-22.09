@Path("/echo")
public class EchoResource {
    // ...
    @GET @Path("{spell}")
    @Produces(MediaType.TEXT_PLAIN)
    public String echo(@PathParam String spell) {
        return service.echo(spell);
    }
}
