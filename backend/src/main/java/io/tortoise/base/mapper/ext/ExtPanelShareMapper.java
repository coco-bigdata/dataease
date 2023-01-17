package io.tortoise.base.mapper.ext;

import io.tortoise.base.domain.PanelShare;
import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.dto.panel.PanelSharePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExtPanelShareMapper {

    int batchInsert(@Param("shares") List<PanelShare> shares);

    List<PanelSharePo> query(Map<String, Object> param);

    List<PanelShare> queryWithResource(GridExample example);
}
