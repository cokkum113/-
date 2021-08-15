package com.example.assignment2_su;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class PizzaDaoTest {

    private AnnotationConfigApplicationContext ac;
    private PizzaDao pizzaDao;

    @Before
    public void before() {
        ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        pizzaDao = ac.getBean(PizzaDao.class);
    }

    @Test
    public void add_addPizzaOrder_CheckInsertThis() throws SQLException {
        Pizza pizza1 = new Pizza();
        pizza1.setPizzaNumber(1);
        pizza1.setOrderQuantity(2);
        pizza1.setOrderStatus("주문완료");
        pizzaDao.insert(pizza1);
    }

    /*
    @Test
    public void select_SelectAll_ReturnOrderList() throws SQLException {
        List<Pizza> list = pizzaDao.select();

        for (Pizza pizza : list) {
            assertThat(pizza.getOrderStatus()).isEqualTo("주문접수");
        }
    }

     */

    @Test
    public void delete_WhenOrderDeleteByUsingOrderNumber_DeleteThat() {
        pizzaDao.delete(1);
    }

    @Test
    @Scheduled(fixedDelay = 3000L)
    public void test_ThreeSecondSleep_WakeUpAfterThreeSecond() {
        System.out.println("Ta-da!");
    }

    @Test
    @Scheduled(cron = "0 1 * * * *")
    public void test_RunEveryMinute_Print() {
        System.out.println("Ta-da!");
    }




/*
    @Test
    public void getAll_CheckAllOrder_CheckSameOrders(){
        List<Pizza> pizzas0 = pizzaDao.getAll();
        assertThat(pizzas0.size()).isEqualTo(0);

        pizzaDao.add(pizza1);
        List<Pizza> pizzas1 = pizzaDao.getAll();
        assertThat(pizzas1.size()).isEqualTo(1);
    }

*/


}
