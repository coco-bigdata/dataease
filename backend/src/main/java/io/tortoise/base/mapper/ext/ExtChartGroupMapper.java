package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.chart.ChartGroupRequest;
import io.tortoise.dto.chart.ChartGroupDTO;

import java.util.List;

public interface ExtChartGroupMapper {
    List<ChartGroupDTO> search(ChartGroupRequest ChartGroup);
}
