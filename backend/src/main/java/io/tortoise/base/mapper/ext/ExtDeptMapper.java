package io.tortoise.base.mapper.ext;

import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.controller.request.BaseTreeRequest;
import io.tortoise.controller.sys.request.SimpleTreeNode;
import io.tortoise.dto.SysDeptDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExtDeptMapper {

    @Update(" update sys_dept set sub_count = sub_count+1 where dept_id = #{deptId} ")
    int incrementalSubcount(@Param("deptId") Long deptId);

    @Update(" update sys_dept set sub_count = sub_count-1 where dept_id = #{deptId} and sub_count > 0")
    int decreasingSubcount(@Param("deptId") Long deptId);


    @Delete("<script> " +
            "        delete from sys_dept where dept_id in " +
            "        <foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id}</foreach> " +
            "</script>")
    int batchDelete(@Param("ids") List<Long> ids);


    List<SimpleTreeNode> allNodes();

    List<SimpleTreeNode> nodesByExample(GridExample example);

    List<SysDeptDTO> searchTree(BaseTreeRequest request);

}
