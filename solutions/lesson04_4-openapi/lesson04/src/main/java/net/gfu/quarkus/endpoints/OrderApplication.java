package net.gfu.quarkus.endpoints;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@Produces("application/json")
@Consumes("application/json")
@OpenAPIDefinition(
        tags = {
                @Tag(name="order", description="Operationen zur Bestellung"),
        },
        info = @Info(
                title="Order API",
                version = "0.0.1",
                contact = @Contact(
                        name = "André Lambertz",
                        url = "https://anderscore.com",
                        email = "andre.lambertz@anderscore.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class OrderApplication extends Application {
}
