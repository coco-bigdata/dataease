package io.tortoise.auth.api.dto;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class DynamicMenuDto implements Serializable {

    private String path;

    private String component;

    private String redirect;

    private String name;

    private MenuMeta meta;

    private Long pid;

    private Long id;

    private String permission;

    private Boolean hidden;

    private Integer type;

    private Integer menuSort;

    private Boolean isPlugin;

    private Boolean noLayout;

    private List<DynamicMenuDto> children;

}
