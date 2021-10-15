package data.model;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Einfaches, in-memory repository fuer Bestellungen
 */
@ApplicationScoped
public class OrderRepository {

    private static Long nonce = 1L;

    private final static Map<Long,Order> db = new HashMap<>();
    static {
        Long id = 1L;
        db.put(id, new Order(
                id,
                Timestamp.from(Instant.now(Clock.systemUTC())),
                42L,
                Status.LOST,
                List.of("Funghi"),
                new BigDecimal("6.5")
        ));
    }

    // Aktualisieren oder anlegen
    public void save(Order o){
        if(o.getOrderId() == null){
            o.setOrderId(generateID());
        }
        db.put(o.getOrderId(),o);
    }

    // Nach Id suchen
    public Order findById(Long id){
        return db.get(id);
    }

    // Alle Orders zurück geben
    public Collection<Order> findAll() {
        return db.values();
    }

    public void delete(Order o){
        if(o.getOrderId() == null) {
            throw new IllegalArgumentException("Not in Database");
        } else {
            db.remove(o.getOrderId());
        }
    }

    // ID erzeugen
    public static Long generateID(){
        return ++nonce;
    }

}
