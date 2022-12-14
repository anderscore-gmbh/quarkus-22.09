package net.gfu.quarkus;

import net.gfu.quarkus.model.Order;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.List;

@RegisterRestClient
public interface PizzaOrderClient {

    @GET
    @Produces("application/json")
    List<Order> orders();
}