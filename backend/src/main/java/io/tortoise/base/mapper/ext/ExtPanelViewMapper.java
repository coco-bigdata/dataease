package io.tortoise.base.mapper.ext;

import io.tortoise.dto.panel.PanelViewDto;

import java.util.List;

public interface ExtPanelViewMapper {

    List<PanelViewDto> groups(String userId);

    List<PanelViewDto> views(String userId);
}
