package com.example.community.post.domain.content;

public abstract class Content {
    String contentText;

     protected Content(String contentText) {
        checkText(contentText); // contentText를 받아서 호출하게 되면 자식 클래스가 checkText를 오버라이딩한 메서드가 실행
        this.contentText = contentText;
    }

    // 검증,체크를 추상화 한다
    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
