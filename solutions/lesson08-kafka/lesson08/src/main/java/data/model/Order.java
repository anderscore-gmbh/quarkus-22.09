package data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pizza_order")
public class Order implements Serializable {


    public Order(){

    }

    public Order(Long orderId, Timestamp orderDateTime, Long customerId, Status status, List<Pizza> pizzaList, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
        this.customerId = customerId;
        this.status = status;
        this.pizzaList = pizzaList;
        this.totalPrice = totalPrice;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Timestamp orderDateTime;

    private Long customerId;
    private Status status;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Pizza> pizzaList;

    private BigDecimal totalPrice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Timestamp orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDateTime=" + orderDateTime +
                ", customerId=" + customerId +
                ", status=" + status +
                ", pizzaList=" + pizzaList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
