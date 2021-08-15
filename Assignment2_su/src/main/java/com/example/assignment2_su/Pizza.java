package com.example.assignment2_su;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pizza {

    private int OrderNumber;
    private int pizzaNumber;
    private int orderQuantity;
    private String orderStatus;

    public Pizza(int OrderNumber, int pizzaNumber, int orderQuantity, String orderStatus) {
        this.OrderNumber = OrderNumber;
        this.pizzaNumber = pizzaNumber;
        this.orderQuantity = orderQuantity;
        this.orderStatus = orderStatus;
    }

    public Pizza() {

    }
}
