package net.gfu.quarkus.endpoints;

import data.model.Status;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
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
                .then().statusCode(404);
    }

    @Test
    public void testHasOrder1(){
        // Creating JSON-Order Object
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
        given().contentType("application/json").body(obj, ObjectMapperType.JSONB).when().post("/");

        // Test
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
                .body(obj, ObjectMapperType.JSONB)
                .when().post("/")
                .then().statusCode(422).body(is(emptyString()));
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
                .body(obj, ObjectMapperType.JSONB)
                .when().post("/")
                .then().statusCode(201).body(is(emptyString()));
    }
}