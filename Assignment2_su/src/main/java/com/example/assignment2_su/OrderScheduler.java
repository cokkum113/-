/*
package com.example.assignment2_su;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
// 스프링 스케줄러는 별도의 설정이 없으면 무조건 싱글쓰레드가 모두 처리해
// @Scheduled가 붙은 모든 매서든를 싱글쓰레드가 처리함

@Component
@RequiredArgsConstructor
public class OrderScheduler {
    private static final long STATUS_CHANGE_INTERVAL = 60 * 1000L;

    private final JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = STATUS_CHANGE_INTERVAL)
    public void job() {
        jdbcTemplate.update("update pizza_order set OrderNumber='변경할 값' WHERE OrderNumber='해당값'");
        //요거 작성하기
        OrderStatus.ReceivingOrder.getV();
    }


}

 */
