package net.gfu.quarkus.endpoints;

import data.model.OrderRepository;
import data.model.Status;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Readiness
public class PizzaCapacityCheck implements HealthCheck {

    @Inject
    OrderRepository repository;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("Queue Size");
        long deliveries = repository.find("status", Status.IN_PROGRESS).count();
        if (deliveries > 42) {
            builder.down().withData("queue", "" + deliveries);
        } else {
            builder.up().withData("queue", "" + deliveries);
        }
        return builder.build();
    }
}
