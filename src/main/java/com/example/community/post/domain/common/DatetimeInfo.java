package com.example.community.post.domain.common;

import java.time.LocalDateTime;

public class DatetimeInfo {

    private boolean isEdited;
    private LocalDateTime dateTime;

    // 입력받을 필요없다 지금 시간과 초기값을 여기서 처리 해줄거기 때문에
    public DatetimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void updateEditDatetime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
