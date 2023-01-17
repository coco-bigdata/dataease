package io.tortoise.base.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Organization implements Serializable {
    private String id;

    private String name;

    private String description;

    private Long createTime;

    private Long updateTime;

    private static final long serialVersionUID = 1L;
}
