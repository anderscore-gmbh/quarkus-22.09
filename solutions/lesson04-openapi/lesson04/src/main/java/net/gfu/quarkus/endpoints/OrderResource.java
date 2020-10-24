package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.OrderRepository;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("orders")
@Produces("application/json")
@Consumes("application/json")
@OpenAPIDefinition(
        tags = {
                @Tag(name="order", description="Operationen zur Bestellung"),
        },
        info = @Info(
                title="Order API",
                version = "0.0.1",
                contact = @Contact(
                        name = "Jan Luehr",
                        url = "https://anderscore.com",
                        email = "jan.luehr@anderscore.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class OrderResource {

    // CRUD repository
    @Inject
    private OrderRepository repository;

    // Create
    @POST
    @Transactional
    @Operation(operationId = "create", description = "Bestllung im System einstellen")
    @APIResponse(responseCode = "200", description = "Erfolgreich")
    @APIResponse(responseCode = "402", description = "Keine Daten zur Bestellung")
    @APIResponse(responseCode = "422", description = "Die Bestellung ist dem System bekannt")
    public Response create(
            @Parameter(description = "Alle Daten zur Bestellung", required = true) Order o, @Context UriInfo uriInfo){
        if(o == null) {
            return Response.status(402).build();
        }
        if(o.getOrderId() != null) {
            return Response.status(422).build();
        }
        this.repository.persist(o);

        URI newUri;
        try {
            String path = uriInfo.getAbsolutePath().toString() + "/" + o.getOrderId();
            newUri = new URI(path);
        } catch (URISyntaxException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.created(newUri).build();
    }

    // Read
    @GET
    @Path("{id}")
    @Transactional
    @Operation(operationId = "read", description = "Eine Bestellungen laden")
    @APIResponse(responseCode = "200", description = "Erfolgreich")
    @APIResponse(responseCode = "404", description = "Bestellung nicht im System hinterlegt")
    public Response read(
            @Parameter(description = "orderId der Bestellung", required = true)
            @PathParam("id") Long id){
        Order order = this.repository.findById(id);
        if(order == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    // Update
    @PATCH
    @Transactional
    @Operation(operationId = "update", description = "Bestehende Bestellung aktualisieren")
    @APIResponse(responseCode = "200", description = "Erfolgreich")
    @APIResponse(responseCode = "404", description = "Bestellung nicht im System hinterlegt")
    public Response update(
            @Parameter(description = "Alle Daten zur Bestellung", required = true)
                    Order o ){
        if(o.getOrderId() == null || this.repository.findById(o.getOrderId()) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.repository.persist(o);
        return Response.noContent().build();
    }

    // Delete
    @DELETE
    @Transactional
    @Path("{id}")
    @Operation(operationId = "delete", description = "Bestehende Bestellung löschen")
    @APIResponse(responseCode = "204", description = "Erfolgreich - Kein Inhalt")
    @APIResponse(responseCode = "404", description = "Bestellung nicht im System hinterlegt")
    public Response delete(
            @Parameter(description = "orderId der Bestellung", required = true)
            @PathParam("id") Long id){
        Order o = this.repository.findById(id);
        if(o == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.repository.delete(o);
        return Response.noContent().build();
    }

    // Index
    @GET
    @Transactional
    @Operation(operationId = "index", description = "Alle Bestellungen abrufen")
    @APIResponse(responseCode = "200", description = "Erfolgreich")
    public List<Order> index(){
        return this.repository.listAll();
    }



    
}

