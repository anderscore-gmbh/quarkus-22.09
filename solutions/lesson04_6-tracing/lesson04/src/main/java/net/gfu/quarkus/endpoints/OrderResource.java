package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.OrderRepository;
import org.jboss.logging.Logger;

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
import java.util.List;

@Path("orders")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private static final Logger LOG = Logger.getLogger(OrderResource.class);

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
    public List<Order> index(){
        LOG.info("index called");
        return this.repository.listAll();
    }
}
