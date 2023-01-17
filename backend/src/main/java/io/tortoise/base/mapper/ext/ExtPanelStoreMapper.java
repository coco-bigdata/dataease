package io.tortoise.base.mapper.ext;

import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.dto.panel.PanelStoreDto;

import java.util.List;

public interface ExtPanelStoreMapper {

    List<PanelStoreDto> query(GridExample example);
}
