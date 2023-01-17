package io.tortoise.base.mapper;

import io.tortoise.base.domain.TestPlan;
import io.tortoise.base.domain.TestPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPlanMapper {
    long countByExample(TestPlanExample example);

    int deleteByExample(TestPlanExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPlan record);

    int insertSelective(TestPlan record);

    List<TestPlan> selectByExampleWithBLOBs(TestPlanExample example);

    List<TestPlan> selectByExample(TestPlanExample example);

    TestPlan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestPlan record, @Param("example") TestPlanExample example);

    int updateByExampleWithBLOBs(@Param("record") TestPlan record, @Param("example") TestPlanExample example);

    int updateByExample(@Param("record") TestPlan record, @Param("example") TestPlanExample example);

    int updateByPrimaryKeySelective(TestPlan record);

    int updateByPrimaryKeyWithBLOBs(TestPlan record);

    int updateByPrimaryKey(TestPlan record);
}
