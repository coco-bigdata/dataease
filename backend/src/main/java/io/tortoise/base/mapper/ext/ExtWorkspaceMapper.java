package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.WorkspaceRequest;
import io.tortoise.dto.WorkspaceDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtWorkspaceMapper {

    List<WorkspaceDTO> getWorkspaceWithOrg(@Param("request") WorkspaceRequest request);
    List<String> getWorkspaceIdsByOrgId(@Param("orgId") String orgId);
}
