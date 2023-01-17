package io.tortoise.auth.service;

import io.tortoise.auth.api.dto.CurrentRoleDto;
import io.tortoise.auth.entity.SysUserEntity;

import java.util.List;

public interface AuthUserService {



    SysUserEntity getUserById(Long userId);

    SysUserEntity getUserByName(String username);

    List<String> roles(Long userId);

    List<String> permissions(Long userId);

    List<CurrentRoleDto> roleInfos(Long userId);

    void clearCache(Long userId);



}
