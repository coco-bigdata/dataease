package io.tortoise.controller.dataset;

import io.tortoise.base.domain.DatasetTable;
import io.tortoise.base.domain.DatasetTableField;
import io.tortoise.base.domain.DatasetTableIncrementalConfig;
import io.tortoise.controller.request.dataset.DataSetTableRequest;
import io.tortoise.datasource.dto.TableFiled;
import io.tortoise.dto.dataset.DataSetTableDTO;
import io.tortoise.service.dataset.DataSetTableService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author gin
 * @Date 2021/2/20 8:29 下午
 */
@RestController
@RequestMapping("dataset/table")
public class DataSetTableController {
    @Resource
    private DataSetTableService dataSetTableService;

    @PostMapping("batchAdd")
    public void batchAdd(@RequestBody List<DataSetTableRequest> datasetTable) throws Exception {
        dataSetTableService.batchInsert(datasetTable);
    }

    @PostMapping("update")
    public DatasetTable save(@RequestBody DataSetTableRequest datasetTable) throws Exception {
        return dataSetTableService.save(datasetTable);
    }

    @PostMapping("delete/{id}")
    public void delete(@PathVariable String id) throws Exception {
        dataSetTableService.delete(id);
    }

    @PostMapping("list")
    public List<DataSetTableDTO> list(@RequestBody DataSetTableRequest dataSetTableRequest) {
        return dataSetTableService.list(dataSetTableRequest);
    }

    @PostMapping("listAndGroup")
    public List<DataSetTableDTO> listAndGroup(@RequestBody DataSetTableRequest dataSetTableRequest) {
        return dataSetTableService.listAndGroup(dataSetTableRequest);
    }

    @PostMapping("get/{id}")
    public DatasetTable get(@PathVariable String id) {
        return dataSetTableService.get(id);
    }

    @PostMapping("getFields")
    public List<TableFiled> getFields(@RequestBody DataSetTableRequest dataSetTableRequest) throws Exception {
        return dataSetTableService.getFields(dataSetTableRequest);
    }

    @PostMapping("getFieldsFromDE")
    public Map<String, List<DatasetTableField>> getFieldsFromDE(@RequestBody DataSetTableRequest dataSetTableRequest) throws Exception {
        return dataSetTableService.getFieldsFromDE(dataSetTableRequest);
    }

    @PostMapping("getPreviewData/{page}/{pageSize}")
    public Map<String, Object> getPreviewData(@RequestBody DataSetTableRequest dataSetTableRequest, @PathVariable Integer page, @PathVariable Integer pageSize) throws Exception {
        return dataSetTableService.getPreviewData(dataSetTableRequest, page, pageSize);
    }

    @PostMapping("sqlPreview")
    public Map<String, Object> getSQLPreview(@RequestBody DataSetTableRequest dataSetTableRequest) throws Exception {
        return dataSetTableService.getSQLPreview(dataSetTableRequest);
    }

    @PostMapping("customPreview")
    public Map<String, Object> customPreview(@RequestBody DataSetTableRequest dataSetTableRequest) throws Exception {
        return dataSetTableService.getCustomPreview(dataSetTableRequest);
    }

    @PostMapping("incrementalConfig")
    public DatasetTableIncrementalConfig incrementalConfig(@RequestBody DatasetTableIncrementalConfig datasetTableIncrementalConfig) throws Exception {
        return dataSetTableService.incrementalConfig(datasetTableIncrementalConfig);
    }

    @PostMapping("save/incrementalConfig")
    public void saveIncrementalConfig(@RequestBody DatasetTableIncrementalConfig datasetTableIncrementalConfig) throws Exception {
        dataSetTableService.saveIncrementalConfig(datasetTableIncrementalConfig);
    }

    @PostMapping("datasetDetail/{id}")
    public Map<String, Object> datasetDetail(@PathVariable String id) {
        return dataSetTableService.getDatasetDetail(id);
    }

    @PostMapping("excel/upload")
    public Map<String, Object> excelUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return dataSetTableService.excelSaveAndParse(file);
    }

    @PostMapping("checkDorisTableIsExists/{id}")
    public Boolean checkDorisTableIsExists(@PathVariable String id) throws Exception {
        return dataSetTableService.checkDorisTableIsExists(id);
    }
}
