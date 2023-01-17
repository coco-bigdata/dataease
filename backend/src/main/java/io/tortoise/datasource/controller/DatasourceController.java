package io.tortoise.datasource.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.tortoise.base.domain.Datasource;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.commons.utils.PageUtils;
import io.tortoise.commons.utils.Pager;
import io.tortoise.controller.request.DatasourceUnionRequest;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.datasource.dto.DBTableDTO;
import io.tortoise.datasource.service.DatasourceService;
import io.tortoise.dto.DatasourceDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("datasource")
@RestController
public class DatasourceController {

    @Resource
    private DatasourceService datasourceService;

    @PostMapping("/add")
    public Datasource addDatasource(@RequestBody Datasource datasource) {
        return datasourceService.addDatasource(datasource);
    }

    @PostMapping("/validate")
    public void validate(@RequestBody Datasource datasource) throws Exception {
        datasourceService.validate(datasource);
    }

    @GetMapping("/list")
    public List<DatasourceDTO> getDatasourceList() throws Exception {
        DatasourceUnionRequest request = new DatasourceUnionRequest();
        request.setUserId(String.valueOf(AuthUtils.getUser().getUserId()));
        return datasourceService.getDatasourceList(request);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<DatasourceDTO>> getDatasourceList(@RequestBody BaseGridRequest request, @PathVariable int goPage, @PathVariable int pageSize) throws Exception {
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        // return PageUtils.setPageInfo(page, datasourceService.getDatasourceList(request));
        return PageUtils.setPageInfo(page, datasourceService.gridQuery(request));
    }

    @PostMapping("/delete/{datasourceID}")
    public void deleteDatasource(@PathVariable(value = "datasourceID") String datasourceID) throws Exception {
        datasourceService.deleteDatasource(datasourceID);
    }

    @PostMapping("/update")
    public void updateDatasource(@RequestBody Datasource Datasource) {
        datasourceService.updateDatasource(Datasource);
    }

    @PostMapping("/getTables")
    public List<DBTableDTO> getTables(@RequestBody Datasource datasource) throws Exception {
        return datasourceService.getTables(datasource);
    }
}
