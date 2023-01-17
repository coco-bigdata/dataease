package io.tortoise.dto;

import io.tortoise.base.domain.Datasource;
import lombok.Data;

/**
 * Author: wangjiahao
 * Date: 2021-05-18
 * Description:
 */
@Data
public class DatasourceDTO extends Datasource {

    private String privileges;

}
