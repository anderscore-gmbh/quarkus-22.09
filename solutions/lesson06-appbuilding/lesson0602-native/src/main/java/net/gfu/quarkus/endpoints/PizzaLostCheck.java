package net.gfu.quarkus.endpoints;

import data.model.OrderRepository;
import data.model.Status;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Liveness
public class PizzaLostCheck implements HealthCheck {

    @Inject
    private OrderRepository orderRepository;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("Queue Size");
        long deliveries = orderRepository.find("status", Status.LOST).count();
        if (deliveries > 0) {
            builder.down().withData("lostPizzas", "" + deliveries);
        } else {
            builder.up().withData("lostPizzas", "" + deliveries);
        }
        return builder.build();
    }
}
