package io.tortoise.provider;

import io.tortoise.base.domain.DatasetTableField;
import io.tortoise.controller.request.chart.ChartExtFilterRequest;
import io.tortoise.dto.chart.ChartCustomFilterDTO;
import io.tortoise.dto.chart.ChartViewFieldDTO;

import java.util.List;

/**
 * @Author gin
 * @Date 2021/5/17 2:42 下午
 */
public abstract class QueryProvider {
    public abstract Integer transFieldType(String field);

    public abstract String createQueryCountSQL(String table);

    public abstract String createQueryCountSQLAsTmp(String sql);

    public abstract String createSQLPreview(String sql, String orderBy);

    public abstract String createQuerySQL(String table, List<DatasetTableField> fields);

    public abstract String createQuerySQLAsTmp(String sql, List<DatasetTableField> fields);

    public abstract String createQuerySQLWithPage(String table, List<DatasetTableField> fields, Integer page, Integer pageSize, Integer realSize);

    public abstract String createQuerySQLAsTmpWithPage(String sql, List<DatasetTableField> fields, Integer page, Integer pageSize, Integer realSize);

    public abstract String getSQL(String table, List<ChartViewFieldDTO> xAxis, List<ChartViewFieldDTO> yAxis, List<ChartCustomFilterDTO> customFilter, List<ChartExtFilterRequest> extFilterRequestList);

    public abstract String getSQLAsTmp(String table, List<ChartViewFieldDTO> xAxis, List<ChartViewFieldDTO> yAxis, List<ChartCustomFilterDTO> customFilter, List<ChartExtFilterRequest> extFilterRequestList);

    public abstract String searchTable(String table);

    public abstract String getSQLSummary(String table, List<ChartViewFieldDTO> yAxis, List<ChartCustomFilterDTO> customFilter, List<ChartExtFilterRequest> extFilterRequestList);

    public abstract String getSQLSummaryAsTmp(String sql, List<ChartViewFieldDTO> yAxis, List<ChartCustomFilterDTO> customFilter, List<ChartExtFilterRequest> extFilterRequestList);

    public abstract String wrapSql(String sql);

    public abstract String createRawQuerySQL(String table, List<DatasetTableField> fields);

    public abstract String createRawQuerySQLAsTmp(String sql, List<DatasetTableField> fields);
}
