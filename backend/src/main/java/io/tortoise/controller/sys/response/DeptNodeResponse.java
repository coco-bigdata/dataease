package io.tortoise.controller.sys.response;

import io.tortoise.base.domain.SysDept;
import lombok.Data;


@Data
public class DeptNodeResponse extends SysDept {
    private boolean hasChildren;

    private boolean leaf;

    private boolean top;

}
