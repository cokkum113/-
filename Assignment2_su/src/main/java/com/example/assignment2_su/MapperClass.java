package com.example.assignment2_su;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapperClass implements RowMapper<Pizza> {

    @Override
    public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pizza pizza = new Pizza();
        pizza.setOrderNumber(rs.getInt("OrderNumber"));
        pizza.setPizzaNumber(rs.getInt("pizzaNumber"));
        pizza.setOrderQuantity(rs.getInt("orderQuantity"));
        pizza.setOrderStatus(rs.getString("orderStatus"));
        return pizza;
    }
}
