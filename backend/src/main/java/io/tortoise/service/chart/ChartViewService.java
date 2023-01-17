package io.tortoise.service.chart;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.tortoise.base.domain.*;
import io.tortoise.base.mapper.ChartViewMapper;
import io.tortoise.base.mapper.ext.ExtChartGroupMapper;
import io.tortoise.base.mapper.ext.ExtChartViewMapper;
import io.tortoise.commons.utils.AuthUtils;
import io.tortoise.commons.utils.BeanUtils;
import io.tortoise.commons.utils.CommonBeanFactory;
import io.tortoise.controller.request.chart.ChartExtFilterRequest;
import io.tortoise.controller.request.chart.ChartExtRequest;
import io.tortoise.controller.request.chart.ChartGroupRequest;
import io.tortoise.controller.request.chart.ChartViewRequest;
import io.tortoise.datasource.provider.DatasourceProvider;
import io.tortoise.datasource.provider.ProviderFactory;
import io.tortoise.datasource.request.DatasourceRequest;
import io.tortoise.datasource.service.DatasourceService;
import io.tortoise.dto.chart.*;
import io.tortoise.dto.dataset.DataTableInfoDTO;
import io.tortoise.i18n.Translator;
import io.tortoise.provider.QueryProvider;
import io.tortoise.service.dataset.DataSetTableFieldsService;
import io.tortoise.service.dataset.DataSetTableService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author gin
 * @Date 2021/3/1 12:34 下午
 */
@Service
public class ChartViewService {
    @Resource
    private ChartViewMapper chartViewMapper;
    @Resource
    private ExtChartViewMapper extChartViewMapper;
    @Resource
    private DataSetTableService dataSetTableService;
    @Resource
    private DatasourceService datasourceService;
    @Resource
    private DataSetTableFieldsService dataSetTableFieldsService;
    @Resource
    private ExtChartGroupMapper extChartGroupMapper;

    public ChartViewWithBLOBs save(ChartViewWithBLOBs chartView) {
        checkName(chartView);
        long timestamp = System.currentTimeMillis();
        chartView.setUpdateTime(timestamp);
        int i = chartViewMapper.updateByPrimaryKeySelective(chartView);
        if (i == 0) {
            chartView.setId(UUID.randomUUID().toString());
            chartView.setCreateBy(AuthUtils.getUser().getUsername());
            chartView.setCreateTime(timestamp);
            chartView.setUpdateTime(timestamp);
            chartViewMapper.insertSelective(chartView);
        }
        return chartView;
    }

    public List<ChartViewDTO> list(ChartViewRequest chartViewRequest) {
        chartViewRequest.setUserId(String.valueOf(AuthUtils.getUser().getUserId()));
        return extChartViewMapper.search(chartViewRequest);
    }

    public List<ChartViewDTO> listAndGroup(ChartViewRequest chartViewRequest) {
        chartViewRequest.setUserId(String.valueOf(AuthUtils.getUser().getUserId()));
        List<ChartViewDTO> charts = extChartViewMapper.search(chartViewRequest);
        charts.forEach(ele -> ele.setIsLeaf(true));
        // 获取group下的子group
        ChartGroupRequest chartGroupRequest = new ChartGroupRequest();
        chartGroupRequest.setLevel(null);
        chartGroupRequest.setType("group");
        chartGroupRequest.setPid(chartViewRequest.getSceneId());
        chartGroupRequest.setUserId(String.valueOf(AuthUtils.getUser().getUserId()));
        chartGroupRequest.setSort("name asc,create_time desc");
        List<ChartGroupDTO> groups = extChartGroupMapper.search(chartGroupRequest);
        List<ChartViewDTO> group = groups.stream().map(ele -> {
            ChartViewDTO dto = new ChartViewDTO();
            BeanUtils.copyBean(dto, ele);
            dto.setIsLeaf(false);
            dto.setType("group");
            return dto;
        }).collect(Collectors.toList());
        group.addAll(charts);
        return group;
    }

    public ChartViewWithBLOBs get(String id) {
        return chartViewMapper.selectByPrimaryKey(id);
    }

    public void delete(String id) {
        chartViewMapper.deleteByPrimaryKey(id);
    }

    public void deleteBySceneId(String sceneId) {
        ChartViewExample chartViewExample = new ChartViewExample();
        chartViewExample.createCriteria().andSceneIdEqualTo(sceneId);
        chartViewMapper.deleteByExample(chartViewExample);
    }

    public ChartViewDTO getData(String id, ChartExtRequest requestList) throws Exception {
        ChartViewWithBLOBs view = chartViewMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(view)) {
            throw new RuntimeException(Translator.get("i18n_chart_delete"));
        }
        List<ChartViewFieldDTO> xAxis = new Gson().fromJson(view.getXAxis(), new TypeToken<List<ChartViewFieldDTO>>() {
        }.getType());
        List<ChartViewFieldDTO> yAxis = new Gson().fromJson(view.getYAxis(), new TypeToken<List<ChartViewFieldDTO>>() {
        }.getType());
        List<ChartCustomFilterDTO> customFilter = new Gson().fromJson(view.getCustomFilter(), new TypeToken<List<ChartCustomFilterDTO>>() {
        }.getType());
        customFilter.forEach(ele -> ele.setField(dataSetTableFieldsService.get(ele.getFieldId())));

        if (StringUtils.equalsIgnoreCase("text", view.getType()) || StringUtils.equalsIgnoreCase("gauge", view.getType())) {
            xAxis = new ArrayList<>();
            if (CollectionUtils.isEmpty(yAxis)) {
                ChartViewDTO dto = new ChartViewDTO();
                BeanUtils.copyBean(dto, view);
                return dto;
            }
        } else {
            if (CollectionUtils.isEmpty(xAxis) || CollectionUtils.isEmpty(yAxis)) {
                ChartViewDTO dto = new ChartViewDTO();
                BeanUtils.copyBean(dto, view);
                return dto;
            }
        }

        // 过滤来自仪表板的条件
        List<ChartExtFilterRequest> extFilterList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(requestList.getFilter())) {
            for (ChartExtFilterRequest request : requestList.getFilter()) {
                DatasetTableField datasetTableField = dataSetTableFieldsService.get(request.getFieldId());
                request.setDatasetTableField(datasetTableField);
                if (StringUtils.equalsIgnoreCase(datasetTableField.getTableId(), view.getTableId())) {
                    if (CollectionUtils.isNotEmpty(request.getViewIds())) {
                        if (request.getViewIds().contains(view.getId())) {
                            extFilterList.add(request);
                        }
                    } else {
                        extFilterList.add(request);
                    }
                }
            }
        }

        // 获取数据集
        DatasetTable table = dataSetTableService.get(view.getTableId());
        if (ObjectUtils.isEmpty(table)) {
            throw new RuntimeException(Translator.get("i18n_dataset_delete"));
        }
        // 判断连接方式，直连或者定时抽取 table.mode
        List<String[]> data = new ArrayList<>();
        if (table.getMode() == 0) {// 直连
            Datasource ds = datasourceService.get(table.getDataSourceId());
            if (ObjectUtils.isEmpty(ds)) {
                throw new RuntimeException(Translator.get("i18n_datasource_delete"));
            }
            DatasourceProvider datasourceProvider = ProviderFactory.getProvider(ds.getType());
            DatasourceRequest datasourceRequest = new DatasourceRequest();
            datasourceRequest.setDatasource(ds);
            DataTableInfoDTO dataTableInfoDTO = new Gson().fromJson(table.getInfo(), DataTableInfoDTO.class);
            QueryProvider qp = ProviderFactory.getQueryProvider(ds.getType());
            if (StringUtils.equalsIgnoreCase(table.getType(), "db")) {
                datasourceRequest.setTable(dataTableInfoDTO.getTable());
                if (StringUtils.equalsIgnoreCase("text", view.getType()) || StringUtils.equalsIgnoreCase("gauge", view.getType())) {
                    datasourceRequest.setQuery(qp.getSQLSummary(dataTableInfoDTO.getTable(), yAxis, customFilter, extFilterList));
                } else {
                    datasourceRequest.setQuery(qp.getSQL(dataTableInfoDTO.getTable(), xAxis, yAxis, customFilter, extFilterList));
                }
            } else if (StringUtils.equalsIgnoreCase(table.getType(), "sql")) {
                if (StringUtils.equalsIgnoreCase("text", view.getType()) || StringUtils.equalsIgnoreCase("gauge", view.getType())) {
                    datasourceRequest.setQuery(qp.getSQLSummaryAsTmp(dataTableInfoDTO.getSql(), yAxis, customFilter, extFilterList));
                } else {
                    datasourceRequest.setQuery(qp.getSQLAsTmp(dataTableInfoDTO.getSql(), xAxis, yAxis, customFilter, extFilterList));
                }
            }
            data = datasourceProvider.getData(datasourceRequest);
        } else if (table.getMode() == 1) {// 抽取
            // 连接doris，构建doris数据源查询
            Datasource ds = (Datasource) CommonBeanFactory.getBean("DorisDatasource");
            DatasourceProvider datasourceProvider = ProviderFactory.getProvider(ds.getType());
            DatasourceRequest datasourceRequest = new DatasourceRequest();
            datasourceRequest.setDatasource(ds);
            String tableName = "ds_" + table.getId().replaceAll("-", "_");
            datasourceRequest.setTable(tableName);
            QueryProvider qp = ProviderFactory.getQueryProvider(ds.getType());
            if (StringUtils.equalsIgnoreCase("text", view.getType()) || StringUtils.equalsIgnoreCase("gauge", view.getType())) {
                datasourceRequest.setQuery(qp.getSQLSummary(tableName, yAxis, customFilter, extFilterList));
            } else {
                datasourceRequest.setQuery(qp.getSQL(tableName, xAxis, yAxis, customFilter, extFilterList));
            }
            data = datasourceProvider.getData(datasourceRequest);
        }
        if (StringUtils.containsIgnoreCase(view.getType(), "pie") && data.size() > 1000) {
            data = data.subList(0, 1000);
        }

        // 图表组件可再扩展
        List<String> x = new ArrayList<>();
        List<Series> series = new ArrayList<>();
        for (ChartViewFieldDTO y : yAxis) {
            Series series1 = new Series();
            series1.setName(y.getName());
            series1.setType(view.getType());
            series1.setData(new ArrayList<>());
            series.add(series1);
        }
        for (String[] d : data) {
            StringBuilder a = new StringBuilder();
            for (int i = 0; i < xAxis.size(); i++) {
                if (i == xAxis.size() - 1) {
                    a.append(d[i]);
                } else {
                    a.append(d[i]).append("\n");
                }
            }
            x.add(a.toString());
            for (int i = xAxis.size(); i < xAxis.size() + yAxis.size(); i++) {
                int j = i - xAxis.size();
                try {
                    series.get(j).getData().add(new BigDecimal(StringUtils.isEmpty(d[i]) ? "0" : d[i]));
                } catch (Exception e) {
                    series.get(j).getData().add(new BigDecimal(0));
                }
            }
        }
        // table组件
        List<ChartViewFieldDTO> fields = new ArrayList<>();
        List<Map<String, Object>> tableRow = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(xAxis)) {
            fields.addAll(xAxis);
        }
        fields.addAll(yAxis);
        data.forEach(ele -> {
            Map<String, Object> d = new HashMap<>();
            for (int i = 0; i < fields.size(); i++) {
                ChartViewFieldDTO chartViewFieldDTO = fields.get(i);
                if (chartViewFieldDTO.getDeType() == 0 || chartViewFieldDTO.getDeType() == 1) {
                    d.put(fields.get(i).gettortoiseName(), StringUtils.isEmpty(ele[i]) ? "" : ele[i]);
                } else if (chartViewFieldDTO.getDeType() == 2 || chartViewFieldDTO.getDeType() == 3) {
                    d.put(fields.get(i).gettortoiseName(), new BigDecimal(StringUtils.isEmpty(ele[i]) ? "0" : ele[i]).setScale(2, RoundingMode.HALF_UP));
                }
            }
            tableRow.add(d);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("x", x);
        map.put("series", series);
        map.put("fields", fields);
        map.put("tableRow", tableRow);

        ChartViewDTO dto = new ChartViewDTO();
        BeanUtils.copyBean(dto, view);
        dto.setData(map);
        return dto;
    }

    private void checkName(ChartViewWithBLOBs chartView) {
//        if (StringUtils.isEmpty(chartView.getId())) {
//            return;
//        }
        ChartViewExample chartViewExample = new ChartViewExample();
        ChartViewExample.Criteria criteria = chartViewExample.createCriteria();
        if (StringUtils.isNotEmpty(chartView.getId())) {
            criteria.andIdNotEqualTo(chartView.getId());
        }
        if (StringUtils.isNotEmpty(chartView.getSceneId())) {
            criteria.andSceneIdEqualTo(chartView.getSceneId());
        }
        if (StringUtils.isNotEmpty(chartView.getName())) {
            criteria.andNameEqualTo(chartView.getName());
        }
        List<ChartViewWithBLOBs> list = chartViewMapper.selectByExampleWithBLOBs(chartViewExample);
        if (list.size() > 0) {
            throw new RuntimeException(Translator.get("i18n_name_cant_repeat_same_group"));
        }
    }

    public Map<String, Object> getChartDetail(String id) {
        Map<String, Object> map = new HashMap<>();
        ChartViewWithBLOBs chartViewWithBLOBs = chartViewMapper.selectByPrimaryKey(id);
        map.put("chart", chartViewWithBLOBs);
        if (ObjectUtils.isNotEmpty(chartViewWithBLOBs)) {
            Map<String, Object> datasetDetail = dataSetTableService.getDatasetDetail(chartViewWithBLOBs.getTableId());
            map.putAll(datasetDetail);
        }
        return map;
    }

    public List<ChartView> viewsByIds(List<String> viewIds) {
        ChartViewExample example = new ChartViewExample();
        example.createCriteria().andIdIn(viewIds);
        return chartViewMapper.selectByExample(example);
    }

    public ChartViewWithBLOBs findOne(String id) {
        return chartViewMapper.selectByPrimaryKey(id);
    }
}
