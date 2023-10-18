package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("Configuration TEST")
    void configurationTest() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService01 = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // when
        MemberRepository memberRepository01 = memberService01.getMemberRepository();
        MemberRepository memberRepository02 = orderService.getMemberRepository();

        System.out.println("memberService01 -> memberRepository01 = " + memberRepository01);
        System.out.println("orderService -> memberRepository02 = " + memberRepository02);
        System.out.println("memberRepository = " + memberRepository);

        // then
        assertThat(memberRepository01).isSameAs(memberRepository02).isSameAs(memberRepository);
    }

}
