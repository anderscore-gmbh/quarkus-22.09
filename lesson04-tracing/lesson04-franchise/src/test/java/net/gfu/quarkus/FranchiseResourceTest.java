package net.gfu.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FranchiseResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/lesson04-franchise")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}