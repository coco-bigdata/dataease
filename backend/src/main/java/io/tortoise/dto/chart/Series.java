package io.tortoise.dto.chart;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author gin
 * @Date 2021/3/1 3:51 下午
 */
@Getter
@Setter
public class Series {
    private String name;
    private String type;
    private List<BigDecimal> data;
}
