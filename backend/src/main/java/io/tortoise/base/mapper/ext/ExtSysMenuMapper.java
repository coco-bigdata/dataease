package io.tortoise.base.mapper.ext;

import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.controller.sys.request.SimpleTreeNode;

import java.util.List;

public interface ExtSysMenuMapper {

    List<SimpleTreeNode> allNodes();

    List<SimpleTreeNode> nodesByExample(GridExample example);
}
