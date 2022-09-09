package data.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Einfaches, in-memory repository fuer Bestellungen
 */
@ApplicationScoped
public class OrderRepository {
    Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @Inject
    EntityManager em;

    // Aktualisieren oder anlegen
    @Transactional
    public void save(Order o){
        logger.info("Order: "+ o.toString());
        em.merge(o);
    }

    // Nach Id suchen
    @Transactional
    public Order findById(Long id){
        return em.find(Order.class, id);
    }

    // Alle Orders zurückgeben
    @Transactional
    public Collection<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Transactional
    public void delete(Order o){
        Order managedOrder = em.find(Order.class, o.getOrderId());
        em.remove(managedOrder);
    }

}