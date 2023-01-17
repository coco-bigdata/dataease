package io.tortoise.base.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class ApiScenarioModule implements Serializable {
    private String id;

    private String projectId;

    private String name;

    private String parentId;

    private Integer level;

    private Long createTime;

    private Long updateTime;

    private Double pos;

    private static final long serialVersionUID = 1L;
}
