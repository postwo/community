package com.example.community.post.domain.content;

import com.example.community.post.domain.common.DatetimeInfo;

public abstract class Content {
    String contentText;
    final DatetimeInfo datetimeInfo;

     protected Content(String contentText ) {
         checkText(contentText); // contentText를 받아서 호출하게 되면 자식 클래스가 checkText를 오버라이딩한 메서드가 실행
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    // 수정 된 내용
    public void updateContent (String updateContent) {
        checkText(updateContent); // contentText를 받아서 호출하게 되면 자식 클래스가 checkText를 오버라이딩한 메서드가 실행
        this.contentText = updateContent;
        this.datetimeInfo.updateEditDatetime();
    }

    // 검증,체크를 추상화 한다
    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
