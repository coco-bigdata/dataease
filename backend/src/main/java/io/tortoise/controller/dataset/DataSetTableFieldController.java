package io.tortoise.controller.dataset;

import io.tortoise.base.domain.DatasetTableField;
import io.tortoise.service.dataset.DataSetFieldService;
import io.tortoise.service.dataset.DataSetTableFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author gin
 * @Date 2021/2/24 4:28 下午
 */
@RestController
@RequestMapping("/dataset/field")
public class DataSetTableFieldController {
    @Resource
    private DataSetTableFieldsService dataSetTableFieldsService;

    @Autowired
    private DataSetFieldService dataSetFieldService;

    @PostMapping("list/{tableId}")
    public List<DatasetTableField> list(@PathVariable String tableId) {
        DatasetTableField datasetTableField = DatasetTableField.builder().build();
        datasetTableField.setTableId(tableId);
        return dataSetTableFieldsService.list(datasetTableField);
    }

    @PostMapping("batchEdit")
    public void batchEdit(@RequestBody List<DatasetTableField> list) {
        dataSetTableFieldsService.batchEdit(list);
    }

    @PostMapping("fieldValues/{fieldId}")
    public List<Object> fieldValues(@PathVariable String fieldId) {
        return dataSetFieldService.fieldValues(fieldId);
    }
}
