package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.SysAuthRequest;
import io.tortoise.dto.SysAuthDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtSysAuthMapper {
    List<SysAuthDetailDTO> searchAuth(SysAuthRequest request);

    Boolean authExist(@Param("authSource") String authSource, @Param("authTarget") String authTarget);

    String findAuthId(@Param("authSource") String authSource,
                      @Param("authSourceType") String authSourceType,
                      @Param("authTarget") String authTarget,
                      @Param("authTargetType") String authTargetType);

    Boolean checkTreeNoManageCount(@Param("userId") Long userId , @Param("modelType") String modelType, @Param("nodeId") String nodeId);



}
