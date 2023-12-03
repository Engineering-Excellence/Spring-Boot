package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

//@Service    // Component-Scan 방식
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     *
     * @param member 회원 정보 저장 변수 선언
     * @return Long id 저장된 회원의 id 반환
     */
    @Override
    public Long join(Member member) {
        validateDuplicateMember(member);   // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 조회
     *
     * @param memberId 회원 id 저장 변수 선언
     * @return Optional<Member> 회원 정보 반환
     */
    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * 회원 전체 조회
     *
     * @return List<Member> 전체 회원 리스트 반환
     */
    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    private void validateDuplicateMember(@NonNull Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
