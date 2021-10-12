package net.gfu.quarkus.endpoints;

import org.eclipse.microprofile.health.*;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@Readiness
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
