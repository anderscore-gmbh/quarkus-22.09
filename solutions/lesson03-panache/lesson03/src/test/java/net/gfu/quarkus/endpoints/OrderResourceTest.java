package net.gfu.quarkus.endpoints;

import data.model.Order;
import data.model.Pizza;
import data.model.Status;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

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

        // Build Order
        Long orderID = null;
        Timestamp orderDateTime = Timestamp.from(Instant.now(Clock.systemUTC()));
        Long customerID = 42L;
        Status status = Status.LOST;
        BigDecimal price = BigDecimal.TEN;

        List<Pizza> pizzaList = List.of(new Pizza(null, "Funghi", price));
        Order order = new Order(orderID, orderDateTime, customerID, status, pizzaList, price);

//        JsonObject order = Json.createObjectBuilder()
//                .add("orderDateTime", ZonedDateTime.now().toString())
//                .add("customerId", 42L)
//                .add("status", Status.LOST.toString())
//                .add("pizzaList",Json.createArrayBuilder()
//                        .add(Json.createObjectBuilder()
//                                .add("name", "Funghi")
//                                .add("price", "6.5"))
//                        .build())
//                .add("totalPrize", "6.5").build();

        given()
                .contentType("application/json")
                .body(order, ObjectMapperType.JSONB)
                .when()
                .post("/");

        // Test
        given()
                .when().get("1")
                .then().statusCode(200)
                .body("status", equalTo("LOST"));
    }

    @Test
    public void testCannotCreateOrderWithGivenID(){

        // Build Order
        Long orderID = 1L;
        Timestamp orderDateTime = Timestamp.from(Instant.now(Clock.systemUTC()));
        Long customerID = 42L;
        Status status = Status.LOST;
        BigDecimal price = BigDecimal.TEN;

        List<Pizza> pizzaList = List.of(new Pizza(null, "Funghi", price));
        Order order = new Order(orderID, orderDateTime, customerID, status, pizzaList, price);

//        JsonObject order = Json.createObjectBuilder()
//                .add("customerId", 42L)
//                .add("orderId",42L).build();

        // Test
        given().contentType("application/json")
                .body(order, ObjectMapperType.JSONB)
                .when().post("/")
                .then().statusCode(422).body(is(emptyString()));
    }

    @Test
    public void testCreateOrder(){

        // Build Order
        Long orderID = null;
        Timestamp orderDateTime = Timestamp.from(Instant.now(Clock.systemUTC()));
        Long customerID = 42L;
        Status status = Status.LOST;
        BigDecimal price = BigDecimal.TEN;

        List<Pizza> pizzaList = List.of(new Pizza(null, "Funghi", price));
        Order order = new Order(orderID, orderDateTime, customerID, status, pizzaList, price);

//        JsonObject order = Json.createObjectBuilder()
//                .add("orderDateTime", ZonedDateTime.now().toString())
//                .add("customerId", 42L)
//                .add("status", Status.LOST.toString())
//                .add("pizzaList",Json.createArrayBuilder()
//                        .add(Json.createObjectBuilder()
//                                .add("name", "Funghi")
//                                .add("price", "6.5"))
//                        .build())
//                .add("totalPrize", "6.5").build();

        // Test
        given()
                .contentType("application/json")
                .body(order, ObjectMapperType.JSONB)
                .when().post("/")
                .then().statusCode(201).body(is(emptyString()));
    }
}
