package net.gfu.quarkus.endpoints;

import data.model.Order;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

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
            .then().statusCode(404);
    }

    @Test
    public void testHasOrder1(){
        given()
                .when().get("1")
                .then().statusCode(200)
                .body("status", equalTo("LOST"));
    }


    @Test
    public void testCannotCreateOrderWithGivenID(){
        JsonObject obj = Json.createObjectBuilder()
                .add("customerId", 42L)
                .add("orderId",42L).build();
        given().contentType("application/json")
                .body(obj)
                .when().post("/")
                .then().statusCode(422).body(is(emptyString()));
    }

    @Test
    public void testCreateOrder(){
        JsonObject obj = Json.createObjectBuilder()
                .add("customerId", 42L)
                .add("pizzaList",Json.createArrayBuilder().add("Funghi").build())
                .add("totalPrize", "7").build();
        given().contentType("application/json")
                .body(obj)
                .when().post("/")
                .then().statusCode(201).body(is(emptyString()));


    }
}