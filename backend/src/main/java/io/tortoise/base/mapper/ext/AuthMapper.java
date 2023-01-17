package io.tortoise.base.mapper.ext;



import io.tortoise.auth.api.dto.CurrentRoleDto;
import io.tortoise.auth.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AuthMapper {



    List<String> roleCodes(@Param("userId") Long userId);


    List<String> permissions(@Param("userId") Long userId);

    List<String> permissionsAll();

    List<Long> userMenuIds(@Param("userId") Long userId);


    SysUserEntity findUser(@Param("userId") Long userId);

    SysUserEntity findUserByName(@Param("username") String username);


    List<CurrentRoleDto> roles(@Param("userId") Long userId);

}
