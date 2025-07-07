package com.example.community.user.application.interfaces;

import com.example.community.user.domain.User;

public interface UserRelationRepository {

    //userid값을 넘기지 않고 User 객체를 넘기는 이유는
    //id를 넘겼을때보다 이점이 있기 때문이다
    //변경이 있는 도메인 객체 전체를 던지는 것을 선호 그이유는 유저의 카운트도 같이 업데이트 되어야 하기 때문이다
    //트랜잭션 단위로 변경이 일어나야 하는 것들은 같이 메소드를 하나로 묶어주면 유지보수가 쉬어지는 장점이 있다

    //만약에 Userid만 던졌다면 select를 한 번 더 하거나 내부적으로 유저들의  카운트를 한 번더 가져와야 하거나
    //아니면 메소드를 넘겨줘야 하는 추가적인 유지 정보들이 있다면 매번 인터페이스들의 변경이 일어나게 된다

    //팔로우가 되어있는지 조회
    boolean isAlreadyFollow(User user, User targetUser);

    // 팔로우
    void save(User user, User targetUser);

    // un팔로우
    void delete(User user, User targetUser);
}
