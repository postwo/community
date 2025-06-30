package com.example.community.domain;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo userInfo;

    public User(Long id, UserInfo userInfo) {
        this.id = id;
        this.userInfo = userInfo;
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
}
