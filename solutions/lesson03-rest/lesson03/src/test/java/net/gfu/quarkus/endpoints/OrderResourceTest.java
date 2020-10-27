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
        //try {
            new A().doSth();
        //} catch (Throwable e) {
            //throw new RuntimeException(e);
        //    System.out.println("Gefangen");
        //}


    }

    class A {
        public void doSth(){
            throw new PizzaTooBigException();
        }
    }

    public static class PizzaTooBigException extends RuntimeException {

    }
}
