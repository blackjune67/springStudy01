package hello.core.discounter;

import hello.core.member.Member;

public interface DisCountPolicy {
    // * 할인 대상 금액
    int discount(Member member, int price);
}
