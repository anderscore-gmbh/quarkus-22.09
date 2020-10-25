package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.OrderRepository;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

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
public class OrderResource {

    long gau = 42L;

    // CRUD repository
    @Inject
    private OrderRepository repository;

    // Create
    @POST
    @Transactional
    @Counted(description = "Anzahl Bestellungen", absolute = true)
    public Response create(Order o, @Context UriInfo uriInfo){
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
    public Response read(@PathParam("id") Long id){
        Order order = this.repository.findById(id);
        if(order == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    // Update
    @PATCH
    @Transactional
    public Response update(Order o){
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
    public Response delete(@PathParam("id") Long id){
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
    @Timed(name = "listenAufruf", description = "Ladezeit Startseite", unit = MetricUnits.MILLISECONDS)
    public List<Order> index(){
        return this.repository.listAll();
    }



    
}
