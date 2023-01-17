package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.dataset.DataSetGroupRequest;
import io.tortoise.dto.dataset.DataSetGroupDTO;

import java.util.List;
import java.util.Map;

public interface ExtDataSetGroupMapper {
    List<DataSetGroupDTO> search(DataSetGroupRequest ChartGroup);

    Map<String, String> searchIds(String id, String type);
}
