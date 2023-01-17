package io.tortoise.auth.api.dto;

import io.tortoise.auth.entity.SysUserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class CurrentUserDto extends SysUserEntity implements Serializable {

    private List<CurrentRoleDto> roles;

    private List<String> permissions;
}
