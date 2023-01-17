package io.tortoise.base.mapper;

import io.tortoise.base.domain.LoadTest;
import io.tortoise.base.domain.LoadTestExample;
import io.tortoise.base.domain.LoadTestWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoadTestMapper {
    long countByExample(LoadTestExample example);

    int deleteByExample(LoadTestExample example);

    int deleteByPrimaryKey(String id);

    int insert(LoadTestWithBLOBs record);

    int insertSelective(LoadTestWithBLOBs record);

    List<LoadTestWithBLOBs> selectByExampleWithBLOBs(LoadTestExample example);

    List<LoadTest> selectByExample(LoadTestExample example);

    LoadTestWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoadTestWithBLOBs record, @Param("example") LoadTestExample example);

    int updateByExampleWithBLOBs(@Param("record") LoadTestWithBLOBs record, @Param("example") LoadTestExample example);

    int updateByExample(@Param("record") LoadTest record, @Param("example") LoadTestExample example);

    int updateByPrimaryKeySelective(LoadTestWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LoadTestWithBLOBs record);

    int updateByPrimaryKey(LoadTest record);
}
