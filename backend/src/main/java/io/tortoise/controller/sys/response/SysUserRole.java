package io.tortoise.controller.sys.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {

    private Long roleId;

    private String roleName;
}
