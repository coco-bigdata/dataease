package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.chart.ChartViewRequest;
import io.tortoise.dto.chart.ChartViewDTO;

import java.util.List;

public interface ExtChartViewMapper {
    List<ChartViewDTO> search(ChartViewRequest request);
}
