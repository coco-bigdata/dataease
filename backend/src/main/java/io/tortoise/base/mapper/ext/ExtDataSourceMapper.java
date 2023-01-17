package io.tortoise.base.mapper.ext;

import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.controller.request.DatasourceUnionRequest;
import io.tortoise.dto.DatasourceDTO;

import java.util.List;

public interface ExtDataSourceMapper {

    List<DatasourceDTO> query(GridExample example);

    List<DatasourceDTO> queryUnion(DatasourceUnionRequest request);



}
