package io.tortoise.base.mapper.ext;

import io.tortoise.plugins.common.dto.PluginSysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtPluginSysMenuMapper {

    void savePluginMenu(@Param("menuList") List<PluginSysMenu> menuList);

    int deletePluginMenu();
}
