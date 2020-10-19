package data.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    public Order(){

    }

    public Order(Long orderId, Date orderDateTime, Long customerId, Status status, List<String> pizzaList, BigDecimal totalPrice) {
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

    private List<String> pizzaList;

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

    public List<String> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<String> pizzaList) {
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
}
