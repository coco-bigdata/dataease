package io.tortoise.dto.dataset;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author gin
 * @Date 2021/2/23 8:47 下午
 */
@Setter
@Getter
public class DataTableInfoDTO {
    private String table;
    private String sql;
    private String data;// file path
    private List<DataTableInfoCustomUnion> list;
}
