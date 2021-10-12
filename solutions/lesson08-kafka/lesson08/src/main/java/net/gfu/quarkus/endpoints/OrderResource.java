package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.OrderRepository;
import data.model.Status;
import net.gfu.quarkus.messaging.order.OrderKafka;

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

@Path("orders")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class OrderResource {

    @Inject
    OrderKafka orderKafka;

    // CRUD repository
    @Inject
    OrderRepository repository;

    // Create
    @POST
    @Transactional
    public Response create(Order o, @Context UriInfo uriInfo){
        if(o == null) {
            return Response.status(402).build();
        }
        if(o.getOrderId() != null) {
            return Response.status(422).build();
        }
        this.repository.persist(o);
        this.notifyKafka(o);

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
        this.notifyKafka(o);
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
    public List<Order> index(){
        return this.repository.listAll();
    }

    private void notifyKafka(Order o){
        if(Status.IN_DELIVERY.equals(o.getStatus())){
            this.orderKafka.produce(o);
        }
    }
}
