package io.tortoise.controller.sys.request;

import lombok.Data;

@Data
public class DeptStatusRequest {

    private Long deptId;

    private boolean status;
}
