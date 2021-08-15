package com.example.assignment2_su;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnumTest {


    // 1. Spring test -> 모호해
    // 2. Enum -> 실습
    // 3. Spring Scheduler -> 구경만
    // 4. Optional
    // 5. Lambda -> 몇개 써봄
    // 6. 스트림 -> 스트림은 실습

    // enum 클래스 하나 만들고 주문 상태를 나타내는 enum
    // DB에는 숫자로 들어감
    // 숫자로 object 찾기
    // 주문접수중 - 제작중 - 배송중 - 배송완료

    enum PizzaOrderStatus {
        주문접수중(0),
        제작중(1),
        배송중(2),
        배송완료(3),
        취소(4);
        private final int orderNum;

        PizzaOrderStatus(int orderNum) {
            this.orderNum = orderNum;
        }

        public static PizzaOrderStatus findByOrderNum(int orderNum) {
            for (PizzaOrderStatus status : PizzaOrderStatus.values()) {
                if (status.orderNum == orderNum) {
                    return status;
                }
            }
            return null;
        }
    }


    // 테스트 해보기 숫자로 enum 찾기

    @Test
    @DisplayName("[OrderStatus Test] check appropriate orderNum and has required status")
    public void findOrderStatusTest() {
        int[] orderNum = new int[]{0, 1, 2, 3};

        for(PizzaOrderStatus status : PizzaOrderStatus.values()) {
            assertThat(status.orderNum).isEqualTo(status.ordinal());
        }

        assertThat(PizzaOrderStatus.findByOrderNum(orderNum[0])).isEqualTo(PizzaOrderStatus.주문접수중);
        assertThat(PizzaOrderStatus.findByOrderNum(orderNum[1])).isEqualTo(PizzaOrderStatus.제작중);
        assertThat(PizzaOrderStatus.findByOrderNum(orderNum[2])).isEqualTo(PizzaOrderStatus.배송중);
        assertThat(PizzaOrderStatus.findByOrderNum(orderNum[3])).isEqualTo(PizzaOrderStatus.배송완료);
    }



}
