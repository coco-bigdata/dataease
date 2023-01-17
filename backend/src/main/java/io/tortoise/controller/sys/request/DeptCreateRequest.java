package io.tortoise.controller.sys.request;

import io.tortoise.base.domain.SysDept;
import lombok.Data;

@Data
public class DeptCreateRequest extends SysDept {

    private boolean top;

}
