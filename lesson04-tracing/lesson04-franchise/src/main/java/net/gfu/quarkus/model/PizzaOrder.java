package net.gfu.quarkus.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class PizzaOrder implements Serializable {


    public PizzaOrder(){

    }

    public PizzaOrder(Long orderId, Date orderDateTime, Long customerId, Status status, List<Pizza> pizzaList, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
        this.customerId = customerId;
        this.status = status;
        this.pizzaList = pizzaList;
        this.totalPrice = totalPrice;
    }

    private Long orderId;

    private Date orderDateTime;

    private Long customerId;
    private Status status;

    private List<Pizza> pizzaList;

    private BigDecimal totalPrice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Date orderDateTime) {
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
        PizzaOrder order = (PizzaOrder) o;
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
