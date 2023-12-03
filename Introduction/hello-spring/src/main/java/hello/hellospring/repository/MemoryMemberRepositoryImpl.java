package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryMemberRepositoryImpl implements MemberRepository {

    private static final Map<Long, Member> store = new ConcurrentHashMap<>(); // 동시성 문제 해결을 위해 공유 변수에서 Concurrent 자료형 사용
    private static final AtomicLong sequence = new AtomicLong(0); // 시퀀스 생성

    @Override
    public Member save(@NonNull Member member) {
        member.setId(sequence.incrementAndGet());
        store.put(member.getId(), member); //   저장 시 시퀀스 증가 후 저장해야 함
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Map<Long, Member> getStore() {
        return store;
    }
}
