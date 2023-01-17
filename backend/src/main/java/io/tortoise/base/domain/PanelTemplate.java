package io.tortoise.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class PanelTemplate implements Serializable {
    private String id;

    private String name;

    private String pid;

    private Integer level;

    private String nodeType;

    private String createBy;

    private Long createTime;

    private String templateType;

    private static final long serialVersionUID = 1L;
}