package io.tortoise.dto.panel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PanelSharePo {

    @ApiModelProperty("节点ID")
    private String id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("节点父ID")
    private String creator;


}
