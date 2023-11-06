package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singleton1 = ac.getBean(SingletonBean.class);
        SingletonBean singleton2 = ac.getBean(SingletonBean.class);
        System.out.println("singleton1 = " + singleton1);
        System.out.println("singleton2 = " + singleton2);
        assertThat(singleton1).isSameAs(singleton2);
        ac.close();
    }

    @Scope("singleton") // * name 지정을 하지 않으면 singleton이 default
    static class SingletonBean {

        @PostConstruct
        void init() {
            System.out.println("SingletonBean.init");

        }

        @PreDestroy
        void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
