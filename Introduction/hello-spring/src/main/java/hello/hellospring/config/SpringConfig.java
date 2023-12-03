package hello.hellospring.config;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryImpl;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepositoryImpl();
    }
}
