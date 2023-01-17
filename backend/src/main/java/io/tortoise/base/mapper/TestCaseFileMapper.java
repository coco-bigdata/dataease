package io.tortoise.base.mapper;

import io.tortoise.base.domain.TestCaseFile;
import io.tortoise.base.domain.TestCaseFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestCaseFileMapper {
    long countByExample(TestCaseFileExample example);

    int deleteByExample(TestCaseFileExample example);

    int insert(TestCaseFile record);

    int insertSelective(TestCaseFile record);

    List<TestCaseFile> selectByExample(TestCaseFileExample example);

    int updateByExampleSelective(@Param("record") TestCaseFile record, @Param("example") TestCaseFileExample example);

    int updateByExample(@Param("record") TestCaseFile record, @Param("example") TestCaseFileExample example);
}
