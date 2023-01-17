package io.tortoise.controller.sys.response;

import io.tortoise.base.domain.SysMenu;
import lombok.Data;

@Data
public class MenuNodeResponse extends SysMenu {

    private boolean hasChildren;

    private boolean top;
}
