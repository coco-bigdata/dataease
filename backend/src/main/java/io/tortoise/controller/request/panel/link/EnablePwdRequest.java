package io.tortoise.controller.request.panel.link;

import lombok.Data;

@Data
public class EnablePwdRequest {

    private String resourceId;

    private boolean enablePwd;
}
