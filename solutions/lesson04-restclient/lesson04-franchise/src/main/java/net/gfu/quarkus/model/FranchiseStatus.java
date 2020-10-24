package net.gfu.quarkus.model;

import java.io.Serializable;
import java.util.List;

public class FranchiseStatus implements Serializable {

    private List<PizzaOrder> pizzaOrders;

    public FranchiseStatus(){}

    public FranchiseStatus(List<PizzaOrder> pizzaOrders){
        this.pizzaOrders = pizzaOrders;
    }

    public List<PizzaOrder> getPizzaOrders() {
        return pizzaOrders;
    }

    public void setPizzaOrders(List<PizzaOrder> pizzaOrders) {
        this.pizzaOrders = pizzaOrders;
    }
}
