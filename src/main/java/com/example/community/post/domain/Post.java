package com.example.community.post.domain;

import com.example.community.user.domain.User;

public class Post {

    private final Long id;

    // 이렇게 user 객체를 참조하게 되면
    // 이렇게 사용하면 테스트 세팅할때 번거로운 단점이 있다
    /*장점
    1.풍부한 도메인 모델링(Rich Domain Modeling)
    → Post에서 author.getName() 등 User의 정보에 바로 접근할 수 있어 객체 지향적인 설계 가능.

    2.의미적으로 명확함
    → 글쓴이가 누군지를 ID로 추적하는 것보다 User 객체로 표현하는 것이 의미적으로 더 명확함.

    3.연관관계 활용 (ORM에서 유리)
    → JPA나 Hibernate 사용 시 @ManyToOne 관계로 매핑 가능 → join 쿼리를 통해 연관된 User 정보를 쉽게 조회할 수 있음.
    * */

    /*이렇게 사용할 시 단점
    1.테스트 및 생성 번거로움
    → 테스트할 때 User 객체까지 미리 만들어서 넣어줘야 하므로 단위 테스트에서 부담이 생김.

    객체 간 순환 참조 위험
    → User도 List<Post>를 가지고 있을 경우 순환 참조 가능성 존재 → 무한 루프, StackOverflow 등 발생할 수 있음 (특히 toString()이나 JSON 변환 시).

    직렬화/역직렬화 시 무거움
    → API 응답 시 User 전체 정보가 포함되어 불필요한 데이터가 노출되거나 무거워질 수 있음.
    */
    private final User author; // 클쓴이

    // 위처럼 객체값으로 안받고 이렇게 따로 받는다
    /*이방식은 위에 내용과 반대로 생각하면 된단 */
//    private final Long authorId;

    private final PostContent content; // 글내용

    public Post(Long id,User author, PostContent content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
    }
}
