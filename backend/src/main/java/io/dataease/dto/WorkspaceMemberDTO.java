package io.dataease.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WorkspaceMemberDTO {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private Long createTime;
    private Long updateTime;
    private String language;
    private String workspaceId;
    private List<String> roleIds = new ArrayList<>();

}
