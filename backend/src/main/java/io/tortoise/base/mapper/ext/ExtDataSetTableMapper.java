package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.dataset.DataSetTableRequest;
import io.tortoise.dto.dataset.DataSetTableDTO;

import java.util.List;

public interface ExtDataSetTableMapper {
    List<DataSetTableDTO> search(DataSetTableRequest request);
}
