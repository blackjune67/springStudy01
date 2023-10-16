package hello.core.beanfind;

import hello.core.discounter.DisCountPolicy;
import hello.core.discounter.FixDiscountPolicy;
import hello.core.discounter.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConifg.class);

    @Test
    @DisplayName("부모타입으로 조회 시, 자식이 둘 이상이 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
//        DisCountPolicy bean = ac.getBean(DisCountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(DisCountPolicy.class)
        );
    }

    @Test
    @DisplayName("부모타입으로 조회 시, 자식이 둘 이상이 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        DisCountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DisCountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DisCountPolicy> beansOfType = ac.getBeansOfType(DisCountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value=" +
                    beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConifg {
        @Bean
        public DisCountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DisCountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
