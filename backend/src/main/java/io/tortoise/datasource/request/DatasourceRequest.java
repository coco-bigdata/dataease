package io.tortoise.datasource.request;

import io.tortoise.base.domain.Datasource;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DatasourceRequest {
    protected String query;
    protected String table;
    protected Datasource datasource;
    private Long pageSize;
    private Long startPage;


}
