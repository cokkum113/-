package com.example.assignment2_su;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PizzaDao {
    private final JdbcTemplate jdbcTemplate;
    private final MapperClass mapperClass;

    public void insert(Pizza pizza) {
        String sql = "INSERT INTO pizza_order (pizzaNumber,orderQuantity,orderStatus) VALUES(?,?,?)";

        jdbcTemplate.update(sql
                , pizza.getPizzaNumber()
                , pizza.getOrderQuantity()
                , pizza.getOrderStatus());
    }

    public Pizza select(int x) {
        String sql = "SELECT * FROM pizza_order where OrderNumber = ?";
        return jdbcTemplate.queryForObject(sql, mapperClass, x);


    }

    public Pizza selectLastValue() {
        String sql = "SELECT * FROM pizza_order ORDER BY OrderNumber DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, mapperClass);
    }



    public void delete(int OrderNumber) {
        String sql = "delete from pizza_order where OrderNumber = ?";
        jdbcTemplate.update(sql, OrderNumber);
    }


}
