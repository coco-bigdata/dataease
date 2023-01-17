package io.tortoise.auth.service;

import io.tortoise.auth.api.dto.DynamicMenuDto;

import java.util.List;

public interface DynamicMenuService {

    List<DynamicMenuDto> load(String userId);
}
