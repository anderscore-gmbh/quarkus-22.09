package net.gfu.quarkus.endpoints;

import data.model.Status;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
                .add("orderDateTime", jsonTime())
                .add("customerId", 42L)
                .add("status", Status.LOST.toString())
                .add("pizzaList",Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("name", "Funghi")
                                .add("price", "6.5"))
                        .build())
                .add("totalPrize", "6.5").build();
        given().contentType("application/json").body(obj).when().post("/");

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
                .body(obj)
                .when().post("/")
                .then().statusCode(422).body(is(emptyString()));
    }

    @Test
    public void testCreateOrder(){
        // Creating JSON
        JsonObject obj = Json.createObjectBuilder()
                .add("orderDateTime", jsonTime())
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
                .then().statusCode(201).body(is(emptyString()));
    }

    public static String jsonTime() {
        // Current time
        Date date = new Date(System.currentTimeMillis());

        // Conversion to ISO 8601
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        return sdf.format(date);
    }
}
