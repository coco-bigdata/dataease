package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.ProjectRequest;
import io.tortoise.dto.ProjectDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtProjectMapper {

    List<ProjectDTO> getProjectWithWorkspace(@Param("proRequest") ProjectRequest request);

    List<String> getProjectIdByWorkspaceId(String workspaceId);

    int removeIssuePlatform(@Param("platform") String platform, @Param("orgId") String orgId);
}
