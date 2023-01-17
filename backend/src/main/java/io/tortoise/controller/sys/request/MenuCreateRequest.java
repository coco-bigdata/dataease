package io.tortoise.controller.sys.request;

import io.tortoise.base.domain.SysMenu;
import lombok.Data;

@Data
public class MenuCreateRequest extends SysMenu {

    private boolean top;
}
