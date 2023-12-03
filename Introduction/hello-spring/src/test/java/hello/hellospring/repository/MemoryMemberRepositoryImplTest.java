package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryImplTest {

    MemoryMemberRepositoryImpl memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
        memberRepository.getStore().clear();  // 메모리 저장소 초기화 작업 수행
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);
        Member result = memberRepository.findById(member.getId()).orElseThrow();

//        System.out.println("result: " + (result == member));
        assertEquals(member, result);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").orElseThrow(() -> new NoSuchElementException("해당 멤버를 찾을 수 없습니다."));
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertThat(result).hasSize(2);
    }
}
