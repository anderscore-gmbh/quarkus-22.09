package net.gfu.quarkus;

import net.gfu.quarkus.model.FranchiseStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/franchise")
public class FranchiseResource {

    private static final Logger LOG = Logger.getLogger(FranchiseResource.class);

    @Inject
    @RestClient
    private PizzaOrderClient pizzaOrderClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FranchiseStatus index() {
        LOG.info("Index called");
        return new FranchiseStatus(pizzaOrderClient.orders());
    }
}