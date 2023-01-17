package io.tortoise.base.mapper.ext;

import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.controller.sys.response.SysUserGridResponse;

import java.util.List;

public interface ExtSysUserMapper {
    List<SysUserGridResponse> query(GridExample example);
}
