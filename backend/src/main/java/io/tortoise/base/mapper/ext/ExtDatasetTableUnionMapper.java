package io.tortoise.base.mapper.ext;

import io.tortoise.base.domain.DatasetTableUnion;
import io.tortoise.dto.dataset.DataSetTableUnionDTO;

import java.util.List;

public interface ExtDatasetTableUnionMapper {
    List<DataSetTableUnionDTO> selectBySourceTableId(String tableId);

    List<DataSetTableUnionDTO> selectByTargetTableId(String tableId);

    List<DataSetTableUnionDTO> selectUsedFieldBySource(DatasetTableUnion datasetTableUnion);

    List<DataSetTableUnionDTO> selectUsedFieldByTarget(DatasetTableUnion datasetTableUnion);
}