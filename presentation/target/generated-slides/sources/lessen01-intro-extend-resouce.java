// Neue Imports
import javax.inject.Inject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

// Klasse um Attribut *service* und Methode *greeting* wie folgt ergänzen
@Inject GreetingService service;

@GET @Path("/greeting/{name}")
@Produces(MediaType.TEXT_PLAIN)
public String greeting(@PathParam String name) {
   return service.greeting(name);
}
// GreetingResource: Weitere Attribute und Methoden
