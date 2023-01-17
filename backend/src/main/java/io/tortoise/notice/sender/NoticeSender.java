package io.tortoise.notice.sender;

import io.tortoise.notice.domain.MessageDetail;
import org.springframework.scheduling.annotation.Async;

public interface NoticeSender {
    @Async
    void send(MessageDetail messageDetail, NoticeModel noticeModel);
}
