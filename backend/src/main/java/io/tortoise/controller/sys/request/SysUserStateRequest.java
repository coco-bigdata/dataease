package io.tortoise.controller.sys.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserStateRequest implements Serializable {

    private Long userId;

    private Long enabled;
}
