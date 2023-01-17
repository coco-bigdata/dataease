package io.tortoise.controller.request.dataset;

import io.tortoise.base.domain.DatasetTableIncrementalConfig;
import io.tortoise.base.domain.DatasetTableTask;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataSetTaskRequest {
    private DatasetTableTask datasetTableTask;
    private DatasetTableIncrementalConfig datasetTableIncrementalConfig;
}
