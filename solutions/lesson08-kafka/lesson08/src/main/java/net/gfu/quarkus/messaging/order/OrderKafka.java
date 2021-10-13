package net.gfu.quarkus.messaging.order;

import data.model.Order;
import data.model.OrderRepository;
import data.model.Status;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.*;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class OrderKafka {

    private Logger logger = Logger.getLogger(OrderKafka.class);

    @Inject
    OrderRepository repository;

    @Blocking
    @Incoming("order-v1-incoming")
    @Transactional
    public void consume(Order o) {
        logger.info(String.format("Recived order %s",o.toString()));
        this.repository.persist(o);

    }

    @Inject @Channel("order-v1-delivery")
    Emitter<Order> emitter;

    public void produce(Order o) {
        logger.info(String.format("Order is ready for delivery %s",o));
        emitter.send(Message.of(o));
    }
}
