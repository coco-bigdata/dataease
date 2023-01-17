package io.tortoise.base.mapper.ext;

import io.tortoise.controller.request.BaseTreeRequest;
import io.tortoise.dto.VAuthModelDTO;

import java.util.List;

public interface ExtVAuthModelMapper {
    List<VAuthModelDTO> searchTree(BaseTreeRequest request);

}
