package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.OrderRepository;
import data.model.Status;
import org.eclipse.microprofile.faulttolerance.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Path("orders")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    // CRUD repository
    @Inject
    OrderRepository repository;

    // Create
    @POST
    @Transactional
    @CircuitBreaker(successThreshold = 5, requestVolumeThreshold = 4,
            failureRatio = 0.75, delay = 1000)
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
        if(Status.IN_DELIVERY.equals(o.getStatus())){
            deliverPizza(o);
        }
        this.repository.persist(o);
        return Response.noContent().build();
    }

    // Delete
    @DELETE
    @Path("{id}")
    @Transactional
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
    @Timeout(250) // 250 ms Timeout
    @Fallback(fallbackMethod = "sorry") // Returns an empty list
    @Retry(maxRetries = 5) // Try it for 5 times before falling back
    public List<Order> index(){
        return this.repository.listAll();
    }

    // Fallback, index times out
    private List<Order> sorry(){
        return new ArrayList<>();
    }

    // Pizza-Ausfahren braucht viel Zeit
    @Asynchronous
    @Bulkhead(value = 5) // Maximal 5 Lieferfahrzeuge.
    private Future deliverPizza(Order o) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            return CompletableFuture.failedFuture(e);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }
}
