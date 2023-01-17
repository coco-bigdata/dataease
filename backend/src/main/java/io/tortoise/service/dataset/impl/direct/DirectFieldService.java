package io.tortoise.service.dataset.impl.direct;

import com.google.gson.Gson;
import io.tortoise.base.domain.DatasetTable;
import io.tortoise.base.domain.DatasetTableField;
import io.tortoise.base.domain.Datasource;
import io.tortoise.commons.utils.CommonBeanFactory;
import io.tortoise.datasource.provider.DatasourceProvider;
import io.tortoise.datasource.provider.ProviderFactory;
import io.tortoise.datasource.request.DatasourceRequest;
import io.tortoise.datasource.service.DatasourceService;
import io.tortoise.dto.dataset.DataTableInfoDTO;
import io.tortoise.provider.QueryProvider;
import io.tortoise.service.dataset.DataSetFieldService;
import io.tortoise.service.dataset.DataSetTableFieldsService;
import io.tortoise.service.dataset.DataSetTableService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service("directDataSetFieldService")
public class DirectFieldService implements DataSetFieldService {


    @Resource
    private DataSetTableFieldsService dataSetTableFieldsService;

    @Resource
    private DataSetTableService dataSetTableService;


    @Resource
    private DatasourceService datasourceService;

    @Override
    public List<Object> fieldValues(String fieldId) {


        List<DatasetTableField> list = dataSetTableFieldsService.getListByIds(new ArrayList<String>() {{
            add(fieldId);
        }});
        if (CollectionUtils.isEmpty(list)) return null;

        DatasetTableField field = list.get(0);
        String tableId = field.getTableId();
        if (StringUtils.isEmpty(tableId)) return null;
        DatasetTable datasetTable = dataSetTableService.get(tableId);
        if (ObjectUtils.isEmpty(datasetTable) || StringUtils.isEmpty(datasetTable.getName())) return null;
        String tableName = datasetTable.getName();

        DatasourceRequest datasourceRequest = new DatasourceRequest();
        DatasourceProvider datasourceProvider = null;
        if (datasetTable.getMode() == 0) {// 直连
            if (StringUtils.isEmpty(datasetTable.getDataSourceId())) return null;
            Datasource ds = datasourceService.get(datasetTable.getDataSourceId());
            datasourceProvider = ProviderFactory.getProvider(ds.getType());
            datasourceRequest = new DatasourceRequest();
            datasourceRequest.setDatasource(ds);
            DataTableInfoDTO dataTableInfoDTO = new Gson().fromJson(datasetTable.getInfo(), DataTableInfoDTO.class);
            QueryProvider qp = ProviderFactory.getQueryProvider(ds.getType());
            if (StringUtils.equalsIgnoreCase(datasetTable.getType(), "db")) {
                datasourceRequest.setTable(dataTableInfoDTO.getTable());
                datasourceRequest.setQuery(qp.createQuerySQL(dataTableInfoDTO.getTable(), Collections.singletonList(field)));
            } else if (StringUtils.equalsIgnoreCase(datasetTable.getType(), "sql")) {
                datasourceRequest.setQuery(qp.createQuerySQLAsTmp(dataTableInfoDTO.getSql(), Collections.singletonList(field)));
            }
        } else if (datasetTable.getMode() == 1) {// 抽取
            // 连接doris，构建doris数据源查询
            Datasource ds = (Datasource) CommonBeanFactory.getBean("DorisDatasource");
            datasourceProvider = ProviderFactory.getProvider(ds.getType());
            datasourceRequest = new DatasourceRequest();
            datasourceRequest.setDatasource(ds);
            tableName = "ds_" + datasetTable.getId().replaceAll("-", "_");
            datasourceRequest.setTable(tableName);
            QueryProvider qp = ProviderFactory.getQueryProvider(ds.getType());
            datasourceRequest.setQuery(qp.createQuerySQL(tableName, Collections.singletonList(field)));
        }

        try {
            List<String[]> rows = datasourceProvider.getData(datasourceRequest);
            List<Object> results = rows.stream().map(row -> row[0]).distinct().collect(Collectors.toList());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
