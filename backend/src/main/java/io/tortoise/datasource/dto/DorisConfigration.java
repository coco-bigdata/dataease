package io.tortoise.datasource.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DorisConfigration extends MysqlConfigration {

    private Integer httpPort;
}
