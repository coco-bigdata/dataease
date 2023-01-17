package io.tortoise.notice.controller.request;

import io.tortoise.notice.domain.MessageDetail;
import lombok.Data;

import java.util.List;

@Data
public class MessageRequest {
    private List<MessageDetail> messageDetail;
}
