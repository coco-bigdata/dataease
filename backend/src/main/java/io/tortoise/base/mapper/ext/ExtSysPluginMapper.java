package io.tortoise.base.mapper.ext;

import io.tortoise.base.domain.MyPlugin;
import io.tortoise.base.mapper.ext.query.GridExample;

import java.util.List;

public interface ExtSysPluginMapper {

    List<MyPlugin> query(GridExample example);
}
