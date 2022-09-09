package net.gfu.quarkus;

import data.model.Order;
import data.model.Pizza;
import data.model.Status;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import net.gfu.quarkus.endpoints.OrderResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestHTTPEndpoint(OrderResource.class)
public class OrderResourceTest {

    @BeforeEach
    public void beforeEach() {
        // Build Order
        Long orderID = null;
        // Timestamp orderDateTime = Timestamp.from(Instant.now(Clock.systemUTC())); macht aktuell Probleme.
        Long customerID = 23L;
        Status status = Status.LOST;
        BigDecimal price = BigDecimal.TEN;
        List<Pizza> pizzaList = List.of(new Pizza(null, "Funghi", BigDecimal.TEN));

        Order order = new Order(orderID, null, customerID, status, pizzaList, price);

        // Simulating mock.
        given()
                .contentType("application/json")
                .body(order, ObjectMapperType.JSONB)
                .when().post("/");
    }

    @Test
    public void testNoOrder4711(){
        given()
            .when().get("4711") //URL: http://localhost:8081/orders/4711
            .then().statusCode(404); // Not found
    }

    @Test
    public void testHasOrder1(){
        given()
                .when().get("1") //URL: http://localhost:8081/orders/
                .then().statusCode(200)
                .body("status", equalTo("LOST"));
    }

    @Test
    public void testCannotCreateOrderWithGivenID(){
        // Build Order
        Long orderID = 42L;
        // Timestamp orderDateTime = Timestamp.from(Instant.now(Clock.systemUTC())); macht aktuell Probleme.
        Long customerID = 23L;
        Status status = Status.IN_PROGRESS;
        BigDecimal price = BigDecimal.TEN;
        List<Pizza> pizzaList = List.of(new Pizza(null, "Tuna", BigDecimal.TEN));

        Order order = new Order(orderID, null, customerID, status, pizzaList, price);

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
        // Timestamp orderDateTime = Timestamp.from(Instant.now(Clock.systemUTC())); macht aktuell Probleme.
        Long customerID = 23L;
        Status status = Status.IN_DELIVERY;
        BigDecimal price = BigDecimal.TEN;
        List<Pizza> pizzaList = List.of(new Pizza(null, "Spinaci", BigDecimal.TEN));

        Order order = new Order(orderID, null, customerID, status, pizzaList, price);

        // Test
        given()
                .contentType("application/json")
                .body(order, ObjectMapperType.JSONB)
                .when().post("/")
                .then().statusCode(201).body(is(emptyString()));
    }
}
