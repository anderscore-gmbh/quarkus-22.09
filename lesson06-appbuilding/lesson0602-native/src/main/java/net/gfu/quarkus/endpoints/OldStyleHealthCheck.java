package net.gfu.quarkus.endpoints;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class OldStyleHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        double dice = Math.random();
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("dice");
        if(dice >= 0.5) {
            builder.up();
        } else {
            builder.down().withData("valueOfDice", "" + dice);
        }
        return builder.build();
    }
}
