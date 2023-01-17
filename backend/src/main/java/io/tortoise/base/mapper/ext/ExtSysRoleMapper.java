package io.tortoise.base.mapper.ext;

import io.tortoise.base.domain.SysRole;
import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.controller.sys.response.RoleUserItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ExtSysRoleMapper {


    List<SysRole> query(GridExample example);

    int deleteRoleMenu(@Param("roleId") Long roleId);

    int batchInsertRoleMenu(@Param("maps") List<Map<String, Long>> maps);

    List<RoleUserItem> queryAll();

    List<Long> menuIds(@Param("roleId") Long roleId);
}
