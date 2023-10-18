package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 서비스")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int userAprice = statefulService1.order("userA", 10000);
        int userBprice = statefulService2.order("userB", 20000);

//        int price1 = statefulService1.getPrice();
//        int price2 = statefulService2.getPrice();

//        System.out.println("price1 = " + price1);
        System.out.println("userAB = " + userAprice);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}