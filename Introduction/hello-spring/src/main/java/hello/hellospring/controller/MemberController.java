package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberForm;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 자동으로 생성해줌. 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입 권장.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "/members/createMemberForm"; // 회원 가입 폼 반환. 회원 가입 폼 화면 보여주기.
    }

    @PostMapping(value = "/members/new")
    public String create(@NonNull MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 리다이렉트. 회원 가입 완료.
    }
}
