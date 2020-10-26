package net.gfu.quarkus.messaging.order;

import data.model.Order;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class OrderDeserializer extends JsonbDeserializer<Order> {
    public OrderDeserializer(){
        super(Order.class);
    }
}
