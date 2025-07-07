package com.example.community.user.domain;

import com.example.community.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followingCount; // 팔로잉한 수
    private final PositiveIntegerCounter followerCount; // 팔로워 수


    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    //팔로우
    public void follow(User targetUser) {
//        this ex)
//        User userA = new User(1L, ...);
//        User userB = new User(2L, ...);
//
//        userA.follow(userB); // 이때, userA가 this

        if (this.equals(targetUser)) {// 본인이 본인을 팔로우 하면  exception처리
            throw new IllegalArgumentException();
        }

        //follow를 동작하는로직
        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    //언팔로우
    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            // ex) this(userA를 뜻한다)는 즉 userA.equals(userB) 수행!
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    //이거를 private으로 만든이유는 이객체를 사용하는 클라이언트에서 이 메서드를 사용하지 마라라는 의미를 담고 있다
    //즉 클라이언트는 private 메서드를 존재를 아에 몰라야 한다
    //하지만 이런것들이 테스트 때문에 노출하게 되면 노출이 된 테스트코드 유지보수가 어렵게 변한다
    //내부 객체들 까지 코드의 결합이 되었기 때문에 캡슐화가 어려워진다
    //캡슐화가 깨지는걸 방지하기 위해 이렇게 메서드 2개 구현
    private void increaseFollowerCount(){
        followerCount.increase();
    }

    private void decreaseFollowerCount(){
        followerCount.decrease();
    }


    //equals()는 두 객체가 같은지 비교
    //ashCode()는 HashMap, HashSet 같은 컬렉션에서 객체를 구분할 때 사용
    //id 값만 비교하면 무한 참조(순환 참조) 문제를 피할 수 있다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    //객체마다 고유한 정수값(해시코드) 를 생성해서, 그 값을 기준으로 내부에서 정렬 또는 그룹화
    //자바는 hashcode를 통해 빠르게 저장할 위치를 찾고,빠르게 검색 한다
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int followingCount() {
        return followingCount.getCount();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
