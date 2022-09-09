// Neue Imports
import javax.inject.Inject;
import javax.ws.rs.PathParam;

// Klasse um Attribut *service* und Methode *greeting* wie folgt ergänzen
@Inject HelloService service;

@GET @Path("/hello/{name}")
@Produces(MediaType.TEXT_PLAIN)
public String greeting(@PathParam("name") String name) {
   return service.greeting(name);
}
// HelloResource: Weitere Attribute und Methoden
