package io.tortoise.controller.request.chart;

import io.tortoise.base.domain.ChartGroup;
import lombok.Data;


@Data
public class ChartGroupRequest extends ChartGroup {
    private String sort;
    private String userId;
}
