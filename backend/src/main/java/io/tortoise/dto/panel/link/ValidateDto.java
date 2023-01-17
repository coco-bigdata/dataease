package io.tortoise.dto.panel.link;

import lombok.Data;

@Data
public class ValidateDto {

    private boolean valid;

    private boolean enablePwd;

    private boolean passPwd;

    private String resourceId;
}
