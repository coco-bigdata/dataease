package io.tortoise.dto.chart;

import io.tortoise.base.domain.DatasetGroup;
import io.tortoise.commons.model.ITreeBase;
import lombok.Data;

import java.util.List;


@Data
public class ChartGroupDTO extends DatasetGroup implements ITreeBase<ChartGroupDTO> {
    private String label;

    private List<ChartGroupDTO> children;

    private String privileges;
}
