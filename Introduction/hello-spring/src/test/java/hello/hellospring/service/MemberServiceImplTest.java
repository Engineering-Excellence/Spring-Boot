package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceImplTest {

    MemberService memberService;
    MemoryMemberRepositoryImpl memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepositoryImpl();
        memberService = new MemberServiceImpl(memberRepository);  // 서비스 객체 생성 및 주입 작업 수행
    }

    @AfterEach
    void tearDown() {
        memberRepository.getStore().clear();  // 메모리 저장소 초기화 작업 수행
    }

    @Test
    void join() {
        // Given
        Member member = new Member();
        member.setName("hello");

        // When
        Long saveId = memberService.join(member);

        // Then
        Optional<Member> findMember = Optional.of(memberService.findOne(saveId).get());
        assertThat(member.getName()).isEqualTo(findMember.get().getName());
    }

    @Test
    void duplicateMember() {
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // When
        memberService.join(member1);

        // Then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 예외가 발생하면 테스트 성공
//        assertThrows(NullPointerException.class, () -> service.join(member2));

/*
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
*/
/*
        try {
            service.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외가 발생하면 테스트 성공
        }
*/
    }

    @Test
    void findOne() {
    }

    @Test
    void findMembers() {
    }
}
