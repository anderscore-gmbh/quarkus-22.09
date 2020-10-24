package data.model;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * Einfaches, in-memory repository fuer Bestellungen
 */
@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public long inclompleteOrders(){
        return find("select o from Order where status <> LOST and status <> completed").count();
    }

}
