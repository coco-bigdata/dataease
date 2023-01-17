package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.panel.PanelGroupRequest;
import io.tortoise.dto.panel.PanelGroupDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtPanelGroupMapper {

    List<PanelGroupDTO> panelGroupList(PanelGroupRequest request);

    List<PanelGroupDTO> panelGroupListDefault(PanelGroupRequest request);



    //会级联删除pid 下的所有数据
    int deleteCircle(@Param("pid") String pid);

    PanelGroupDTO panelGroup(String id);


}
