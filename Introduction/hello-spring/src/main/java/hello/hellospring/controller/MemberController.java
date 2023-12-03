package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Component-Scan 방식
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 자동으로 생성해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
