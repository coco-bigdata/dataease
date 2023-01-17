package io.tortoise.service.dataset;

import io.tortoise.base.domain.DatasetTableField;
import io.tortoise.base.domain.DatasetTableFieldExample;
import io.tortoise.base.mapper.DatasetTableFieldMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author gin
 * @Date 2021/2/24 2:03 下午
 */
@Service
public class DataSetTableFieldsService {
    @Resource
    private DatasetTableFieldMapper datasetTableFieldMapper;

    public void batchEdit(List<DatasetTableField> list) {
        for (DatasetTableField field : list) {
            save(field);
        }
    }

    public DatasetTableField save(DatasetTableField datasetTableField) {
        if (StringUtils.isEmpty(datasetTableField.getId())) {
            datasetTableField.setId(UUID.randomUUID().toString());
            datasetTableFieldMapper.insert(datasetTableField);
        } else {
            datasetTableFieldMapper.updateByPrimaryKeySelective(datasetTableField);
        }
        return datasetTableField;
    }

    public List<DatasetTableField> list(DatasetTableField datasetTableField) {
        DatasetTableFieldExample datasetTableFieldExample = new DatasetTableFieldExample();
        DatasetTableFieldExample.Criteria criteria = datasetTableFieldExample.createCriteria();
        if (StringUtils.isNotEmpty(datasetTableField.getTableId())) {
            criteria.andTableIdEqualTo(datasetTableField.getTableId());
        }
        if (ObjectUtils.isNotEmpty(datasetTableField.getChecked())) {
            criteria.andCheckedEqualTo(datasetTableField.getChecked());
        }
        datasetTableFieldExample.setOrderByClause("column_index asc");
        return datasetTableFieldMapper.selectByExample(datasetTableFieldExample);
    }

    public void deleteByTableId(String tableId) {
        DatasetTableFieldExample datasetTableFieldExample = new DatasetTableFieldExample();
        datasetTableFieldExample.createCriteria().andTableIdEqualTo(tableId);
        datasetTableFieldMapper.deleteByExample(datasetTableFieldExample);
    }

    public List<DatasetTableField> getListByIds(List<String> ids) {
        DatasetTableFieldExample datasetTableFieldExample = new DatasetTableFieldExample();
        datasetTableFieldExample.createCriteria().andIdIn(ids);
        return datasetTableFieldMapper.selectByExample(datasetTableFieldExample);
    }

    public List<DatasetTableField> getListByIdsEach(List<String> ids) {
        List<DatasetTableField> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ids)) {
            ids.forEach(id -> {
                list.add(datasetTableFieldMapper.selectByPrimaryKey(id));
            });
        }
        return list;
    }

    public List<DatasetTableField> getFieldsByTableId(String id) {
        DatasetTableFieldExample datasetTableFieldExample = new DatasetTableFieldExample();
        datasetTableFieldExample.createCriteria().andTableIdEqualTo(id);
        return datasetTableFieldMapper.selectByExample(datasetTableFieldExample);
    }

    public DatasetTableField get(String id) {
        return datasetTableFieldMapper.selectByPrimaryKey(id);
    }
}
