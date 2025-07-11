package com.example.community.post.domain.content;

import com.example.community.post.domain.common.DatetimeInfo;

public abstract class Content {
    //protected로 하면 상속받은 자식 클래스에서도 필드 접근이 가능하기 때문에, getter 없이도 내부 값에 접근해서 처리 힐 수 있기 때문에 이거 사용
    //외부 클래스나 객체가 내부의 중요한 값(예: contentText, datetimeInfo)을 마음대로 바꾸는 걸 막아서 → 안정성과 일관성을 지키기 위해
    /*값을 외부에서 절대 건드리면 안 되는 경우 → private을 사용하면 되고
    하지만 자식 클래스에서도 활용하고 싶다면 → protected도 고려할 수 있다*/
    protected String contentText;
    protected final DatetimeInfo datetimeInfo;

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
