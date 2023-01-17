package io.tortoise.controller.request.dataset;

import io.tortoise.base.domain.DatasetTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author gin
 * @Date 2021/2/23 3:06 下午
 */
@Setter
@Getter
public class DataSetTableRequest extends DatasetTable {
    private String sort;
    private List<String> tableNames;
    private String row = "1000";
    private String userId;
    private Integer editType;
    private Boolean isRename;
    private List<String> typeFilter;
}
