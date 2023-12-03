package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 자동으로 생성해줌. 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입 권장.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
