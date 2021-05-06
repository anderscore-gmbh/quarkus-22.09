package net.gfu.quarkus.model;

import java.io.Serializable;
import java.util.List;

public class FranchiseStatus implements Serializable {

    private List<Order> orders;

    public FranchiseStatus(){}

    public FranchiseStatus(List<Order> orders){
        this.orders = orders;
    }

    public List<Order> getPizzaOrders() {
        return orders;
    }

    public void setPizzaOrders(List<Order> orders) {
        this.orders = orders;
    }
}
