package io.tortoise.controller.sys.request;

import lombok.Data;

@Data
public class MenuDeleteRequest {

    private Long menuId;

    private Long pid;
}
