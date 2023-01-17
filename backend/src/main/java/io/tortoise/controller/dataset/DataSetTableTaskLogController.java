package io.tortoise.controller.dataset;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.tortoise.base.domain.DatasetTableTaskLog;
import io.tortoise.commons.utils.PageUtils;
import io.tortoise.commons.utils.Pager;
import io.tortoise.dto.dataset.DataSetTaskLogDTO;
import io.tortoise.service.dataset.DataSetTableTaskLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author gin
 * @Date 2021/3/4 1:32 下午
 */
@RestController
@RequestMapping("dataset/taskLog")
public class DataSetTableTaskLogController {
    @Resource
    private DataSetTableTaskLogService dataSetTableTaskLogService;

    @PostMapping("save")
    public DatasetTableTaskLog save(@RequestBody DatasetTableTaskLog datasetTableTaskLog) {
        return dataSetTableTaskLogService.save(datasetTableTaskLog);
    }

    @PostMapping("delete/{id}")
    public void delete(@PathVariable String id) {
        dataSetTableTaskLogService.delete(id);
    }

    @PostMapping("list/{goPage}/{pageSize}")
    public Pager<List<DataSetTaskLogDTO>> list(@RequestBody DatasetTableTaskLog request, @PathVariable int goPage, @PathVariable int pageSize) {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, dataSetTableTaskLogService.list(request));
    }

}
