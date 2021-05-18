package net.gfu.quarkus.endpoints;

import data.model.Status;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.ZonedDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestHTTPEndpoint(OrderResource.class)
public class OrderResourceTest {

    @Test
    public void testNoOrder4711(){
        given()
                .when().get("4711")
                .then().body(is(emptyString()));
    }

    @Test
    public void testHasOrder1(){
        // Test
        given()
                .when().get("1")
                .then().statusCode(200)
                .body("status", equalTo("LOST"));
    }

    @Test
    public void testCreateOrder(){
        // Creating JSON
        JsonObject obj = Json.createObjectBuilder()
                .add("orderDateTime", ZonedDateTime.now().toString())
                .add("customerId", 42L)
                .add("status", Status.LOST.toString())
                .add("pizzaList",Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("name", "Funghi")
                                .add("price", "6.5"))
                        .build())
                .add("totalPrize", "6.5").build();

        // Test
        given().contentType("application/json")
                .body(obj)
                .when().post("/")
                .then().body(is(emptyString()));
    }
}
