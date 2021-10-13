package data.model;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * Einfaches, in-memory repository fuer Bestellungen
 */
@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {


}
