package io.tortoise.base.mapper;

import io.tortoise.base.domain.ApiModule;
import io.tortoise.base.domain.ApiModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApiModuleMapper {
    long countByExample(ApiModuleExample example);

    int deleteByExample(ApiModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApiModule record);

    int insertSelective(ApiModule record);

    List<ApiModule> selectByExample(ApiModuleExample example);

    ApiModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApiModule record, @Param("example") ApiModuleExample example);

    int updateByExample(@Param("record") ApiModule record, @Param("example") ApiModuleExample example);

    int updateByPrimaryKeySelective(ApiModule record);

    int updateByPrimaryKey(ApiModule record);
}
