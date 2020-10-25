package net.gfu.quarkus.endpoints;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class OrderResourceTest {


    @Test
    public void testHelloEndpoint() {
        assertThat("Nothing", true);
    }

}
