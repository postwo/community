package com.example.community.user.application.dto;

//record final 변수로만 구성 되어있고 getter만 가진 객체들은 record형태로 변경이 가능하다
// 그리고 tostring,equalsandhashcode도 자동생성이 된다
public record CreateUserRepositoryDto(String name, String ProfileImageUrl) {

}
