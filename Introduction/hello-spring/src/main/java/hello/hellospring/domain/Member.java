package hello.hellospring.domain;

import lombok.Data;

@Data
public class Member {

    private Long id;    // 시스템이 임의로 저장하는 값
    private String name;
}
