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

    MemoryMemberRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryMemberRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
        repository.getStore().clear();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).orElseThrow();

//        System.out.println("result: " + (result == member));
        assertEquals(member, result);
//        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").orElseThrow(() -> new NoSuchElementException("해당 멤버를 찾을 수 없습니다."));

        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result).hasSize(2);
    }
}
