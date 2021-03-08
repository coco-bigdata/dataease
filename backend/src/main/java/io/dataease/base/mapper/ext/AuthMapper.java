package io.dataease.base.mapper.ext;



import io.dataease.auth.api.dto.CurrentRoleDto;
import io.dataease.auth.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface AuthMapper {



    List<String> roleCodes(@Param("userId") Long userId);


    List<String> permissions(@Param("userId") Long userId);


    SysUserEntity findUser(@Param("userId") Long userId);

    SysUserEntity findUserByName(@Param("username") String username);


    List<CurrentRoleDto> roles(@Param("userId") Long userId);
}
