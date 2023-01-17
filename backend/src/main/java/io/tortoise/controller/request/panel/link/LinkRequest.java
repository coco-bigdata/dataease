package io.tortoise.controller.request.panel.link;

import lombok.Data;

@Data
public class LinkRequest {

    private String resourceId;

    private boolean valid;
}
