package io.tortoise.controller.sys.request;

import lombok.Data;

@Data
public class DeptDeleteRequest {

    private Long deptId;

    private Long pid;
}
