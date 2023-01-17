package io.tortoise.datasource.service;

import com.google.gson.Gson;
import io.tortoise.base.domain.*;
import io.tortoise.base.mapper.*;
import io.tortoise.base.mapper.ext.ExtDataSourceMapper;
import io.tortoise.base.mapper.ext.query.GridExample;
import io.tortoise.commons.exception.DEException;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.commons.utils.CommonThreadPool;
import io.tortoise.commons.utils.LogUtil;
import io.tortoise.controller.request.DatasourceUnionRequest;
import io.tortoise.controller.sys.base.BaseGridRequest;
import io.tortoise.controller.sys.base.ConditionEntity;
import io.tortoise.datasource.dto.DBTableDTO;
import io.tortoise.datasource.provider.DatasourceProvider;
import io.tortoise.datasource.provider.ProviderFactory;
import io.tortoise.datasource.request.DatasourceRequest;
import io.tortoise.dto.DatasourceDTO;
import io.tortoise.dto.dataset.DataTableInfoDTO;
import io.tortoise.exception.TortoiseException;
import io.tortoise.i18n.Translator;
import io.tortoise.service.dataset.DataSetGroupService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class DatasourceService {

    @Resource
    private DatasourceMapper datasourceMapper;
    @Resource
    private ExtDataSourceMapper extDataSourceMapper;
    @Resource
    private DatasetTableMapper datasetTableMapper;
    @Resource
    private DataSetGroupService dataSetGroupService;
    @Resource
    private CommonThreadPool commonThreadPool;

    public Datasource addDatasource(Datasource datasource) {
        checkName(datasource);
        long currentTimeMillis = System.currentTimeMillis();
        datasource.setId(UUID.randomUUID().toString());
        datasource.setUpdateTime(currentTimeMillis);
        datasource.setCreateTime(currentTimeMillis);
        datasource.setCreateBy(String.valueOf(AuthUtils.getUser().getUsername()));
        datasourceMapper.insertSelective(datasource);
        handleConnectionPool(datasource, "add");
        return datasource;
    }

    private void handleConnectionPool(Datasource datasource, String type) {
        commonThreadPool.addTask(() -> {
            try {
                DatasourceProvider datasourceProvider = ProviderFactory.getProvider(datasource.getType());
                DatasourceRequest datasourceRequest = new DatasourceRequest();
                datasourceRequest.setDatasource(datasource);
                datasourceProvider.handleDatasource(datasourceRequest, type);
                LogUtil.info("Succsss to {} datasource connection pool: {}", type, datasource.getName());
            } catch (Exception e) {
                LogUtil.error("Failed to handle datasource connection pool: " + datasource.getName(), e);
            }
        });
    }

    public List<DatasourceDTO> getDatasourceList(DatasourceUnionRequest request) throws Exception {
        request.setSort("update_time desc");
        return extDataSourceMapper.queryUnion(request);
    }

    public List<DatasourceDTO> gridQuery(BaseGridRequest request) {
        //如果没有查询条件增加一个默认的条件
        if (CollectionUtils.isEmpty(request.getConditions())) {
            ConditionEntity conditionEntity = new ConditionEntity();
            conditionEntity.setField("1");
            conditionEntity.setOperator("eq");
            conditionEntity.setValue("1");
            request.setConditions(Arrays.asList(conditionEntity));
        }
        GridExample gridExample = request.convertExample();
        gridExample.setExtendCondition(String.valueOf(AuthUtils.getUser().getUserId()));
        return extDataSourceMapper.query(gridExample);
    }

    public void deleteDatasource(String datasourceId) throws Exception {
        DatasetTableExample example = new DatasetTableExample();
        example.createCriteria().andDataSourceIdEqualTo(datasourceId);
        List<DatasetTable> datasetTables = datasetTableMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(datasetTables)){
            TortoiseException.throwException(datasetTables.size() +  Translator.get("i18n_datasource_not_allow_delete_msg"));
        }
        Datasource datasource = datasourceMapper.selectByPrimaryKey(datasourceId);
        datasourceMapper.deleteByPrimaryKey(datasourceId);
        handleConnectionPool(datasource, "delete");
    }

    public void updateDatasource(Datasource datasource) {
        checkName(datasource);
        datasource.setCreateTime(null);
        datasource.setUpdateTime(System.currentTimeMillis());
        datasourceMapper.updateByPrimaryKeySelective(datasource);
        handleConnectionPool(datasource, "edit");
    }

    public void validate(Datasource datasource) throws Exception {
        DatasourceProvider datasourceProvider = ProviderFactory.getProvider(datasource.getType());
        DatasourceRequest datasourceRequest = new DatasourceRequest();
        datasourceRequest.setDatasource(datasource);
        datasourceProvider.checkStatus(datasourceRequest);
    }

    public List<DBTableDTO> getTables(Datasource datasource) throws Exception {
        Datasource ds = datasourceMapper.selectByPrimaryKey(datasource.getId());
        DatasourceProvider datasourceProvider = ProviderFactory.getProvider(ds.getType());
        DatasourceRequest datasourceRequest = new DatasourceRequest();
        datasourceRequest.setDatasource(ds);
        List<String> tables = datasourceProvider.getTables(datasourceRequest);

        // 获取当前数据源下的db类型数据集
        DatasetTableExample datasetTableExample = new DatasetTableExample();
        datasetTableExample.createCriteria().andTypeEqualTo("db").andDataSourceIdEqualTo(datasource.getId());
        List<DatasetTable> datasetTables = datasetTableMapper.selectByExampleWithBLOBs(datasetTableExample);
        List<DBTableDTO> list = new ArrayList<>();
        for (String name : tables) {
            DBTableDTO dbTableDTO = new DBTableDTO();
            dbTableDTO.setDatasourceId(datasource.getId());
            dbTableDTO.setName(name);
            dbTableDTO.setEnableCheck(true);
            dbTableDTO.setDatasetPath(null);
            for (DatasetTable datasetTable : datasetTables) {
                DataTableInfoDTO dataTableInfoDTO = new Gson().fromJson(datasetTable.getInfo(), DataTableInfoDTO.class);
                if (StringUtils.equals(name, dataTableInfoDTO.getTable())) {
                    dbTableDTO.setEnableCheck(false);
                    List<DatasetGroup> parents = dataSetGroupService.getParents(datasetTable.getSceneId());
                    StringBuilder stringBuilder = new StringBuilder();
                    parents.forEach(ele -> {
                        if (ObjectUtils.isNotEmpty(ele)) {
                            stringBuilder.append(ele.getName()).append("/");
                        }
                    });
                    stringBuilder.append(datasetTable.getName());
                    dbTableDTO.setDatasetPath(stringBuilder.toString());
                    break;
                }
            }
            list.add(dbTableDTO);
        }
        return list;
    }

    public Datasource get(String id) {
        return datasourceMapper.selectByPrimaryKey(id);
    }

    public void initAllDataSourceConnectionPool() {
        List<Datasource> datasources = datasourceMapper.selectByExampleWithBLOBs(new DatasourceExample());
        datasources.forEach(datasource -> {
            try {
                handleConnectionPool(datasource, "add");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void checkName(Datasource datasource) {
        DatasourceExample example = new DatasourceExample();
        DatasourceExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(datasource.getName());
        if (StringUtils.isNotEmpty(datasource.getId())) {
            criteria.andIdNotEqualTo(datasource.getId());
        }
        if (CollectionUtils.isNotEmpty(datasourceMapper.selectByExample(example))) {
            DEException.throwException(Translator.get("i18n_ds_name_exists"));
        }
    }
}
