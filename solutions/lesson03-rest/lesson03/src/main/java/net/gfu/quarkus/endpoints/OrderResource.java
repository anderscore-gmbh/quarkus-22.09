package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("orders")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class OrderResource {

    // CRUD repository
    @Inject
    OrderRepository repository;

    // Create
    @POST
    public void create(Order o){
        this.repository.save(o);
    }

    // Read
    @GET
    @Path("{id}")
    public Order read(@PathParam("id") Long id){
        return this.repository.findById(id);
    }

    // Update
    @PATCH
    public void update(Order o){
        this.repository.save(o);
    }

    // Delete
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        Order o = this.repository.findById(id);
        this.repository.delete(o);
    }

    // Index
    @GET
    public List<Order> index(){
        return new ArrayList<>(this.repository.findAll());
    }
}
