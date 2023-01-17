package io.tortoise.dto.dataset;

import io.tortoise.base.domain.DatasetTableTaskLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author gin
 * @Date 2021/3/9 3:19 下午
 */
@Getter
@Setter
public class DataSetTaskLogDTO extends DatasetTableTaskLog {
    private String name;
}
